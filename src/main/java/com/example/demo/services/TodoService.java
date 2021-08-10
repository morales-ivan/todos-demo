package com.example.demo.services;

import com.example.demo.model.Todo;
import com.example.demo.model.TodoDTO;

import java.util.List;

public interface TodoService {
    List<TodoDTO> getTodos();

    TodoDTO getTodoById(Long id);

    TodoDTO insert(TodoDTO todoDto);

    void updateTodo(Long id, Todo todo);

    void deleteTodo(Long todoId);
}
