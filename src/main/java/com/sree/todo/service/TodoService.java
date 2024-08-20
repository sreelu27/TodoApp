package com.sree.todo.service;

import com.sree.todo.dto.TodoDto;

import java.util.List;

public interface TodoService {

    TodoDto createTodo(TodoDto todoDto);
    TodoDto getTodoById(Long id);
    List<TodoDto> getAllTodos();
    TodoDto updateTodo(Long id, TodoDto todoDto);
    void deleteTodo(Long id);
    TodoDto completeTodo(Long id);
    TodoDto incompleteTodo(Long id);
}
