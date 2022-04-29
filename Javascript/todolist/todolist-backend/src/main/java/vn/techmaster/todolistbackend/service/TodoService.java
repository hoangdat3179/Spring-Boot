package vn.techmaster.todolistbackend.service;


import org.springframework.stereotype.Service;
import vn.techmaster.todolistbackend.model.Todo;
import vn.techmaster.todolistbackend.request.CreateTodoRequest;
import vn.techmaster.todolistbackend.request.UpdateTodoRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class TodoService {
    private List<Todo> todos = new ArrayList<>();

    public TodoService() {
        Random rd = new Random();
        todos.add(new Todo(rd.nextInt(100), "Đi đá bóng", false));
        todos.add(new Todo(rd.nextInt(100), "Đi học", true));
        todos.add(new Todo(rd.nextInt(100), "Đi chơi", false));

    }

    public List<Todo> getTodos(String status) {
        return switch (status) {
            case "true" -> todos.stream().filter(Todo::isStatus).collect(Collectors.toList());
            case "false" -> todos.stream().filter(todo -> !todo.isStatus()).collect(Collectors.toList());
            default -> todos;
        };
    }

    public Todo createTodos(CreateTodoRequest request) {
        Random rd = new Random();
        Todo todo = new Todo();
        todo.setId(rd.nextInt(100));
        todo.setTitle(request.getTitle());
        todo.setStatus(false);

        todos.add(todo);
        return todo;
    }

    public Todo updateTodo(int id, UpdateTodoRequest request) {
        for (Todo todo : todos) {
            if (todo.getId() == id) {
                todo.setTitle(request.getTitle());
                todo.setStatus(request.isStatus());
                return todo;
            }
        }
        return null;
    }

    public void deleteTodo(int id){
        todos.removeIf(todo -> todo.getId()==id);
    }
}
