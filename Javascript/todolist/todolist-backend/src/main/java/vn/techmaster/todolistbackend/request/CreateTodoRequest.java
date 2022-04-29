package vn.techmaster.todolistbackend.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTodoRequest {
    private String title;
}
