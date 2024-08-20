package com.sree.todo.controller;

import com.sree.todo.dto.TodoDto;
import com.sree.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private TodoService todoService;

    @PostMapping()
    public ResponseEntity<TodoDto> createTodo(@RequestBody TodoDto todoDto){
        TodoDto todo = todoService.createTodo(todoDto);
        return new ResponseEntity<>(todo, HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodoById(@PathVariable("id") Long id){
        TodoDto todoDto = todoService.getTodoById(id);
        return ResponseEntity.ok(todoDto);
    }
    @GetMapping()
    public ResponseEntity<List<TodoDto>> getAllTodos(){
        List<TodoDto> todoDtoList = todoService.getAllTodos();
        return ResponseEntity.ok(todoDtoList);
    }
    @PutMapping("{id}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable("id") Long id, @RequestBody TodoDto todoDto){
        TodoDto todoDto1 = todoService.updateTodo(id, todoDto);
        return ResponseEntity.ok(todoDto1);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long id){
        todoService.deleteTodo(id);
        return ResponseEntity.ok("Todo is deleted");
    }


}
