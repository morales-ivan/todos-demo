package com.example.demo.controllers;

import com.example.demo.model.Todo;
import com.example.demo.model.TodoDTO;
import com.example.demo.services.TodoService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todo")
public class TodoController {
    TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    //The function receives a GET request, processes it and gives back a list of Todo as a response.
    @GetMapping
    public ResponseEntity<List<TodoDTO>> getAllTodos() {
        List<TodoDTO> todos = todoService.getTodos();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }
    //The function receives a GET request, processes it, and gives back a list of Todo as a response.
    @GetMapping({"/{todoId}"})
    public ResponseEntity<Todo> getTodo(@PathVariable Long todoId) {
        return new ResponseEntity<Todo>((MultiValueMap<String, String>) todoService.getTodoById(todoId), HttpStatus.OK);
    }
    //The function receives a POST request, processes it, creates a new Todo and saves it to the database, and returns a resource link to the created todo.
    @PostMapping
    public ResponseEntity<TodoDTO> saveTodo(@RequestBody TodoDTO todoDto) {
        TodoDTO responseTodo = todoService.insert(todoDto);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("todo", "/api/v1/todo/" + responseTodo.getTodoId().toString());
        return new ResponseEntity<>(responseTodo, httpHeaders, HttpStatus.CREATED);
    }
    //The function receives a PUT request, updates the Todo with the specified Id and returns the updated Todo
    @PutMapping({"/{todoId}"})
    public ResponseEntity<TodoDTO> updateTodo(@PathVariable("todoId") Long todoId, @RequestBody Todo todo) {
        todoService.updateTodo(todoId, todo);
        return new ResponseEntity<>(todoService.getTodoById(todoId), HttpStatus.OK);
    }
    //The function receives a DELETE request, deletes the Todo with the specified Id.
    @DeleteMapping({"/{todoId}"})
    public ResponseEntity<Todo> deleteTodo(@PathVariable("todoId") Long todoId) {
        todoService.deleteTodo(todoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
