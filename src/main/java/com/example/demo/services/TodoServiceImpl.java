package com.example.demo.services;

import com.example.demo.model.Todo;
import com.example.demo.model.TodoDTO;
import com.example.demo.repositories.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {
    TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<TodoDTO> getTodos() {
        List<Todo> todos = new ArrayList<>(todoRepository.findAll());
        return todos.stream().map(TodoDTO::fromTodo).collect(Collectors.toList());
    }

    @Override
    public TodoDTO getTodoById(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Todo not found"));
        return TodoDTO.fromTodo(todo);
    }

    @Override
    public TodoDTO insert(TodoDTO todoDto) {
        Todo todo = new TodoDTO().toTodo(todoDto);
        Todo responseTodo = todoRepository.save(todo);
        TodoDTO responseTodoDto = new TodoDTO().fromTodo(responseTodo);
        return responseTodoDto;
    }

    @Override
    public void updateTodo(Long id, Todo todo) {
        Todo todoFromDb = todoRepository.findById(id).get();
        System.out.println(todoFromDb.toString());
        todoFromDb.setTodoStatus(todo.getTodoStatus());
        todoFromDb.setDescription(todo.getDescription());
        todoFromDb.setTitle(todo.getTitle());
        todoRepository.save(todoFromDb);
    }

    @Override
    public void deleteTodo(Long todoId) {
        todoRepository.deleteById(todoId);
    }
}
