package com.sree.todo.service.impl;

import com.sree.todo.dto.TodoDto;
import com.sree.todo.entity.Todo;
import com.sree.todo.exception.ResourceNotFoundException;
import com.sree.todo.repository.TodoRepository;
import com.sree.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TodoServiceImpl implements TodoService {
    private TodoRepository todoRepository;
    private ModelMapper modelMapper;
    @Override
    public TodoDto createTodo(TodoDto todoDto) {
        Todo todo = modelMapper.map(todoDto, Todo.class);
        Todo savedTodo = todoRepository.save(todo);
        return modelMapper.map(savedTodo, TodoDto.class);
    }
    @Override
    public TodoDto getTodoById(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found"));
        return modelMapper.map(todo, TodoDto.class);
    }
    @Override
    public List<TodoDto> getAllTodos() {
        List<Todo> todos = todoRepository.findAll();
        List<TodoDto> todoDtoList = todos.stream()
                .map(todo -> modelMapper.map(todo, TodoDto.class))
                .toList();

        return todoDtoList;
    }
    @Override
    public TodoDto updateTodo(Long id, TodoDto todoDto) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found"));
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todo.isCompleted());

        Todo savedTodo = todoRepository.save(todo);
        return modelMapper.map(savedTodo, TodoDto.class);
    }

    @Override
    public void deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found"));
        todoRepository.delete(todo);
    }

    @Override
    public TodoDto completeTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found"));

        todo.setCompleted(Boolean.TRUE);
        Todo savedTodo = todoRepository.save(todo);
        return modelMapper.map(savedTodo, TodoDto.class);
    }
    @Override
    public TodoDto incompleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found"));

        todo.setCompleted(Boolean.FALSE);
        Todo savedTodo = todoRepository.save(todo);
        return modelMapper.map(savedTodo, TodoDto.class);
    }
}
