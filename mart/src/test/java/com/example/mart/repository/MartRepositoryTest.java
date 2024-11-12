package com.example.mart.repository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

import jakarta.transaction.Transactional;

@SpringBootTest
public class MartRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private DeliveryRepository deliveryRepository;

    // C
    @Test
    public void memberInsertTest() {
        memberRepository.save(Member.builder().name("user1").city("서울시").street("187-12").zipcode("15100").build());
        memberRepository.save(Member.builder().name("user2").city("경기도").street("356-11").zipcode("17100").build());
        memberRepository.save(Member.builder().name("user3").city("부산시").street("780-12").zipcode("18100").build());
    }

    @Test
    public void itemInsertTest() {
        // itemRepository.save(Item.builder().name("tshirt").price(25300).quantity(15).build());
        // itemRepository.save(Item.builder().name("shoes").price(101300).quantity(20).build());
        // itemRepository.save(Item.builder().name("pants").price(45000).quantity(10).build());

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
        book.setPrice(10800);
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
        Item item = itemRepository.findById(2L).get();

        Order order = Order.builder()
                .orderDate(LocalDateTime.now())
                .status(OrderStatus.ORDER)
                .member(member)
                .build();
        orderRepository.save(order);

        OrderItem orderItem = OrderItem.builder()
                .price(200000)
                .count(2)
                .order(order)
                .item(item)
                .build();
        orderItemRepository.save(orderItem);

        // item 수량 감소
    }

    @Test
    public void orderItemInsertTest() {
        Item item = itemRepository.findById(1L).get();
        Order order = Order.builder().id(4L).build();

        OrderItem orderItem = OrderItem.builder()
                .price(50000)
                .count(4)
                .order(order)
                .item(item)
                .build();
        orderItemRepository.save(orderItem);
    }

    // R

    @Test
    public void memberAndItemAndOrderListTest() {
        // 주문 내역 조회
        // orderRepository.findAll().forEach(order -> System.out.println(order)); //
        // Order(id=1,orderDate=2024-11-04T13:07:20.144914,status=ORDER)

        // 주문상품 상세 조회
        orderItemRepository.findAll().forEach(orderItem -> {
            System.out.println(orderItem);
            // 상품 상세 조회
            System.out.println(orderItem.getItem());
            // 주문 상세 내역 조회
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
        // 주문 상세 내역 조회
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
        // .city("서울시")
        // .name("user1")
        // .street("255-12")
        // .zipcode("15100")
        // .build();

        Member member = memberRepository.findById(1L).get();
        member.setStreet("189-12");

        // save : insert or update
        // 엔티티 매니저가 있어서 현재 entity 가 new 인지 기존 entity 인지 구분이 가능함
        // new : insert 호출 / 기존 entity : update 호출
        // update 는 무조건 전체 컬럼이 수정되는 형태로 작성됨
        System.out.println(memberRepository.save(member));

        // 1번 주문상품의 아이템(2번 아이템) 가격 변경

        // 아이템 가격 변경
        Item item = itemRepository.findById(2L).get();
        item.setPrice(102000);
        itemRepository.save(item);

        OrderItem orderItem = orderItemRepository.findById(1L).get();
        orderItem.setPrice(102000);
        orderItemRepository.save(orderItem);
    }

    // D
    @Test
    public void memberAndItemAndOrderDeleteTest() {
        // 주문 취소

        // 주문상품 취소
        orderItemRepository.deleteById(1L);

        // 주문 취소
        orderRepository.deleteById(1L);
    }

    // 양방향
    // Order ==> OrderItem 객체 그래프 탐색
    @Transactional
    @Test
    public void testOrderItemList() {

        Order order = orderRepository.findById(2L).get();
        System.out.println(order);
        // Order ==> OrderItem 탐색 시도
        order.getOrderItemList().forEach(orderItem -> System.out.println(orderItem));
    }

    @Transactional
    @Test
    public void testOrderList() {

        Member member = memberRepository.findById(1L).get();
        System.out.println(member);

        // Member ==> Order 탐색 시도
        member.getOrders().forEach(order -> System.out.println(order));
    }

    // 일대일

    @Test
    public void testDeliveryInsert() {

        // 배송 정보 입력
        Delivery delivery = Delivery.builder()
                .city("서울시")
                .street("동소문로1가")
                .zipcode("11051")
                .deliveryStatus(DeliveryStatus.READY)
                .build();

        deliveryRepository.save(delivery);

        // order 와 배송정보 연결
        Order order = orderRepository.findById(2L).get();
        order.setDelivery(delivery);
        orderRepository.save(order);
    }

    @Test
    public void testOrderRead() {
        // order 조회 (+ 배송정보)
        Order order = orderRepository.findById(2L).get();
        System.out.println(order);

        System.out.println(order.getDelivery());
    }

    // 양방향(배송 => 주문)
    @Test
    public void testDeliveryRead() {
        // 배송정보 조회 (+ order)
        Delivery delivery = deliveryRepository.findById(1L).get();
        System.out.println(delivery);
        System.out.println(delivery.getOrder());
    }

    // querydsl
    @Test
    public void testMembers() {
        System.out.println(orderRepository.members());
    }

    @Test
    public void testItems() {
        System.out.println(orderRepository.items());
    }

    @Test
    public void testJoin() {
        List<Object[]> result = orderRepository.joinTest();

        // [Order(id=4, orderDate=2024-11-04T15:57:09.778815, status=ORDER),
        // Member(id=1, name=user1, zipcode=12311, city=1city, street=1street)]
        for (Object[] objects : result) {
            System.out.println(Arrays.toString(objects));
            System.out.println((Order) objects[0]);
            System.out.println((Member) objects[1]);
            System.out.println((OrderItem) objects[2]);
        }
    }

    @Test
    public void testSubQuery() {
        List<Object[]> result = orderRepository.subQueryTest();

        for (Object[] objects : result) {
            System.out.println(Arrays.toString(objects));
            System.out.println((Order) objects[0]);
            System.out.println((Member) objects[1]);
            System.out.println((Long) objects[2]);
        }
    }
}
