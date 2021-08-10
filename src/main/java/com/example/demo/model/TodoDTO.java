package com.example.demo.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TodoDTO {
    public Long todoId;
    public String title;

    public Long getTodoId() {
        return todoId;
    }

    public void setTodoId(Long todoId) {
        this.todoId = todoId;
    }

    public TodoDTO(Long todoId, String title) {
        this.todoId = todoId;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static TodoDTO fromTodo(Todo todo) {
        return new TodoDTO(todo.getId(),todo.getTitle());
    }

    public Todo toTodo(TodoDTO todoDto) {
        return new Todo(todoDto.getTitle());
    }
}
