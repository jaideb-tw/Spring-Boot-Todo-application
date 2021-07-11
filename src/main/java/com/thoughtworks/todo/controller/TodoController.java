package com.thoughtworks.todo.controller;

import com.thoughtworks.todo.model.TodoItem;
import com.thoughtworks.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todo")
public class TodoController {


    private TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/all")
    private ResponseEntity<List<TodoItem>> get() {
        Optional<List<TodoItem>> listOfTodo = todoService.getAllTodo();
        return new ResponseEntity<>(listOfTodo.orElse(null), HttpStatus.OK);
    }

    @PostMapping("/create")
    private ResponseEntity<List<TodoItem>> createTodoList(@RequestBody List<TodoItem> todoItem) throws Exception {
        List<TodoItem> savedItems = todoService.save(todoItem);
        return new ResponseEntity<>(savedItems, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<TodoItem> deleteById(@PathVariable Long id) {
        return new ResponseEntity<>(todoService.deleteById(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/all")
    private ResponseEntity<List<TodoItem>> deleteAll() {
        return new ResponseEntity<>(todoService.deleteAllTodo(), HttpStatus.FORBIDDEN);
    }

    @PutMapping("update/{id}")
    private ResponseEntity<TodoItem> updateTodoItem(@PathVariable Long id) {
        return new ResponseEntity<>(todoService.updateById(id), HttpStatus.ACCEPTED);
    }

}
