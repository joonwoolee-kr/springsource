package com.example.todo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo.dto.TodoDto;
import com.example.todo.entity.Todo;
import com.example.todo.repository.TodoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Service
@Log4j2
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Override
    public List<TodoDto> getList(Boolean completed) {
        // List<Todo> result = todoRepository.findAll();

        // List<TodoDto> list2 = new ArrayList<>();
        // list.forEach(entity -> {
        // list2.add(entityToDto(entity));
        // });

        // List<TodoDto> list = result
        // .stream()
        // .map(todo -> entityToDto(todo))
        // .collect(Collectors.toList());

        List<Todo> result = todoRepository.findByCompleted(completed);
        List<TodoDto> list = result
                .stream()
                .map(todo -> entityToDto(todo))
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public TodoDto getRow(Long id) {
        Todo todo = todoRepository.findById(id).get();

        return entityToDto(todo);
    }

    @Override
    public TodoDto create(TodoDto dto) {
        // dto => entity
        Todo todo = dtoToEntity(dto);
        return entityToDto(todoRepository.save(todo));
    }

    @Override
    public List<TodoDto> getCompletedList() {
        return null;
    }

    @Override
    public Long updateCompleted(TodoDto dto) {
        Todo todo = todoRepository.findById(dto.getId()).get();
        todo.setCompleted(dto.getCompleted());
        Todo updateTodo = todoRepository.save(todo);
        return updateTodo.getId();
    }

    @Override
    public void deleteRow(Long id) {
        todoRepository.deleteById(id);
    }

}
