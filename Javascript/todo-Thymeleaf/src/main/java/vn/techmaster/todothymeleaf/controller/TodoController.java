package vn.techmaster.todothymeleaf.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.todothymeleaf.model.Todo;
import vn.techmaster.todothymeleaf.request.CreateTodoRequest;
import vn.techmaster.todothymeleaf.request.UpdateTodoRequest;
import vn.techmaster.todothymeleaf.service.TodoService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/todos")
    public ResponseEntity<?> getTools(@RequestParam(required = false,defaultValue = "") String status){
        List<Todo> todos = todoService.getTodos(status);
        return ResponseEntity.ok(todos);
    }

    @PostMapping("/todos")
    public ResponseEntity<?> createTodo(@RequestBody CreateTodoRequest request){
        Todo todo = todoService.createTodos(request);
        return ResponseEntity.ok(todo);
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<?> updateTodo(@PathVariable int id, @RequestBody UpdateTodoRequest request){
        Todo todo = todoService.updateTodo(id,request);
        return ResponseEntity.ok(todo);
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable int id){
        todoService.deleteTodo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
