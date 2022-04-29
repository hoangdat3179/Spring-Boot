package vn.techmaster.todothymeleaf.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    private int id;
    private String title;
    private boolean status;
}
