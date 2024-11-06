package com.example.mart.repository;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.mart.entity.constant.DeliveryStatus;
import com.example.mart.entity.constant.OrderStatus;
import com.example.mart.entity.item.Delivery;
import com.example.mart.entity.item.Item;
import com.example.mart.entity.item.Member;
import com.example.mart.entity.item.Order;
import com.example.mart.entity.item.OrderItem;
import com.example.mart.entity.product.Album;
import com.example.mart.entity.product.Book;
import com.example.mart.entity.product.Movie;
import com.example.mart.repository.item.DeliveryRepository;
import com.example.mart.repository.item.ItemRepository;
import com.example.mart.repository.item.MemberRepository;
import com.example.mart.repository.item.OrderItemRepository;
import com.example.mart.repository.item.OrderRepository;

@SpringBootTest
public class MartRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private DeliveryRepository deliveryRepository;

    // C
    @Test
    public void memberInsertTest() {
        IntStream.rangeClosed(1, 3).forEach(i -> {
            Member member = Member.builder()
                    .name("user" + i)
                    .zipcode("1234" + i)
                    .city(i + "city")
                    .street(i + "street")
                    .build();
            memberRepository.save(member);
        });
    }

    @Test
    public void itemInsertTest() {
        // IntStream.rangeClosed(1, 3).forEach(i -> {
        // Item item = Item.builder()
        // .name("item" + i)
        // .price(i * 10000)
        // .quantity(i * 10)
        // .build();
        // itemRepository.save(item);
        // });

        Album album = new Album();
        album.setArtist("로제");
        album.setName("아파트");
        album.setPrice(15200);
        album.setQuantity(15);
        itemRepository.save(album);

        Book book = new Book();
        book.setAuthor("한강");
        book.setIsbn("122ㄱ");
        book.setName("소년이 온다");
        book.setPrice(10000);
        book.setQuantity(15);
        itemRepository.save(book);

        Movie movie = new Movie();
        movie.setActor("폴 메스칼");
        movie.setDirector("리들리 스콧");
        movie.setName("글래디에이터2");
        movie.setPrice(25000);
        movie.setQuantity(300);
        itemRepository.save(movie);
    }

    @Test
    public void orderInsertTest() {
        Member member = memberRepository.findById(1L).get();
        Item item = itemRepository.findById(1L).get();

        Order order = Order.builder()
                .orderDate(LocalDateTime.now())
                .status(OrderStatus.ORDER)
                .member(member)
                .build();
        orderRepository.save(order);

        OrderItem orderItem = OrderItem.builder()
                .price(20000)
                .count(2)
                .order(order)
                .item(item)
                .build();
        orderItemRepository.save(orderItem);

        // item 수량 감소
    }

    // R
    @Test
    public void memberAndItemAndOrderListTest() {
        // 주문 내역 조회
        // orderRepository.findAll().forEach(order -> System.out.println(order));
        // Order(id=1, orderDate=2024-11-04T13:06:11.068589, status=ORDER)

        // 주문상품 상세 조회
        orderItemRepository.findAll().forEach(orderItem -> {
            System.out.println(orderItem);
            // 상품 상세 조회
            System.out.println(orderItem.getItem());
            // 주문 상세 조회
            System.out.println(orderItem.getOrder());
            // 주문자 상세 조회
            System.out.println(orderItem.getOrder().getMember());
        });
    }

    @Test
    public void memberAndItemAndOrderRowTest() {
        OrderItem orderItem = orderItemRepository.findById(1L).get();

        // 주문상품 상세 조회
        System.out.println(orderItem);
        // 상품 상세 조회
        System.out.println(orderItem.getItem());
        // 주문 상세 조회
        System.out.println(orderItem.getOrder());
        // 주문자 상세 조회
        System.out.println(orderItem.getOrder().getMember());
    }

    // U
    @Test
    public void memberAndItemAndOrderUpdateTest() {
        // 주문자의 주소 변경
        // Member member = Member.builder()
        // .id(1L)
        // .name("user1")
        // .city("1city")
        // .street("1street")
        // .zipcode("12311")
        // .build();
        Member member = memberRepository.findById(1L).get();
        member.setZipcode("12311");

        // save: insert or update
        // entity 매니저를 통해 현재 entity가 new 인지 기존 entity 인지 구분 가능
        // new - insert 호출 / 기존 - update 호출
        // update는 무조건 전체 컬럼이 수정되는 형태로 작성됨
        System.out.println(memberRepository.save(member));

        // 1번 주문상품의 아이템(1번 아이템) 가격 변경
        // 아이템 수량, 가격 변경
        Item item = itemRepository.findById(1L).get();
        item.setPrice(35000);
        itemRepository.save(item);

        OrderItem orderItem = orderItemRepository.findById(1L).get();
        orderItem.setPrice(35000);
        orderItemRepository.save(orderItem);
    }

    @Test
    public void memberAndItemAndOrderDeleteTest() {
        // 주문상품 취소
        orderItemRepository.deleteById(1L);

        // 주문 취소
        orderRepository.deleteById(1L);

    }

    // 양방향
    // Order => OrderItem 객체 그래프 탐색
    @Transactional
    @Test
    public void testOrderItemListTest() {
        Order order = orderRepository.findById(4L).get();
        System.out.println(order);
        // Order => OrderItem 탐색 시도
        order.getOrderItemList().forEach(orderItem -> System.out.println(orderItem));
    }

    @Transactional
    @Test
    public void testOrderListTest() {
        Member member = memberRepository.findById(1L).get();
        System.out.println(member);
        // Member => Order 탐색 시도
        member.getOrders().forEach(order -> System.out.println(order));
    }

    // 일대일
    @Test
    public void testDeliveryInsert() {
        // delivery 정보 입력
        Delivery delivery = Delivery.builder()
                .city("서울시")
                .street("동소문로1가")
                .zipcode("11051")
                .deliveryStatus(DeliveryStatus.READY)
                .build();

        deliveryRepository.save(delivery);

        // order 및 delivery 정보 연결
        Order order = orderRepository.findById(4L).get();
        order.setDelivery(delivery);
        orderRepository.save(order);
    }

    @Test
    public void testOrderRead() {
        // order 조회(+ delivery 정보)
        Order order = orderRepository.findById(4L).get();
        System.out.println(order);
        System.out.println(order.getDelivery());
    }

    // 양방향(delivery => order)
    @Test
    public void testDeliveryRead() {
        // delivery 정보 조회(+ order)
        Delivery delivery = deliveryRepository.findById(1L).get();
        System.out.println(delivery);
        System.out.println(delivery.getOrder());
    }
}
