package com.example.mart.repository;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.example.mart.entity.cascade.Child;
import com.example.mart.entity.cascade.Parent;
import com.example.mart.entity.sports.Locker;
import com.example.mart.entity.sports.SportsMember;
import com.example.mart.repository.cascade.ChildRepository;
import com.example.mart.repository.cascade.ParentRepository;
import com.example.mart.repository.sports.LockerRepository;
import com.example.mart.repository.sports.SportsMemberRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class ParentRepositoryTest {

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private ChildRepository childRepository;

    @Test
    public void testParentInsert() {

        Parent parent = Parent.builder().name("parent3").build();

        IntStream.rangeClosed(1, 3).forEach(i -> {
            Child child = Child.builder().name("child" + i).parent(parent).build();

            // parent.getChilds().add(child); // child 정보는 db에 추가되지 않음
            childRepository.save(child);
        });
        // parentRepository.save(parent);
    }

    @Test
    public void testParentInsert2() {

        Parent parent = Parent.builder().name("parent3").build();

        IntStream.rangeClosed(1, 3).forEach(i -> {
            Child child = Child.builder().name("child" + i).parent(parent).build();

            parent.getChilds().add(child);

        });
        parentRepository.save(parent);
    }

    @Test
    public void testChildRead() {
        // 자식2 정보 조회(+ 부모조회)
        Child child = childRepository.findById(2L).get();
        System.out.println(child);
        System.out.println(child.getParent());
    }

    //

    @Test
    public void testParentDelete() {
        // 부모 삭제 시 관련되어 있는 자식 같이 삭제
        // 자식삭제 코드를 작성하지 않고
        parentRepository.deleteById(3L);
    }

    @Commit
    @Transactional
    @Test
    public void testParentDelete2() {
        // 부모 삭제 시 관련되어 있는 자식 같이 삭제
        // 자식삭제 코드를 작성하지 않고

        Parent parent = parentRepository.findById(1L).get();
        // 자식만 삭제, 자식의 일부만 부모를 이용해서 삭제
        parent.getChilds().remove(0);
        // parent.getChilds().remove(1);
        // parent.getChilds().remove(2);
        parentRepository.save(parent);
    }
}
