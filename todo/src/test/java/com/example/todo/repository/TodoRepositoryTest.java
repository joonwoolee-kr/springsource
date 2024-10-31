package com.example.todo.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.todo.entity.Todo;

@SpringBootTest
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void insertTest() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            Todo todo = Todo.builder()
                    .title("할 일" + i)
                    .build();
            todoRepository.save(todo);
        });
    }

    @Test
    public void selectAllTest() {
        todoRepository.findAll().forEach(todo -> System.out.println(todo));
    }

    @Test
    public void selectOneTest() {
        System.out.println(todoRepository.findById(5L).get());
    }

    @Test
    public void updateTest() {
        // completed, important 수정
        Todo todo = todoRepository.findById(3L).get();
        todo.setCompleted(true);
        todo.setImportant(true);
        todoRepository.save(todo);
    }

    @Test
    public void deleteTest() {
        todoRepository.deleteById(9L);
    }

    @Test
    public void completedTest() {
        // 완료 todos
        todoRepository.findByCompleted(true).forEach(todo -> System.out.println(todo));
        // 미완료 todos
        todoRepository.findByCompleted(false).forEach(todo -> System.out.println(todo));
    }
}
