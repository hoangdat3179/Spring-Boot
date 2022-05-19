package vn.techmaster.demosession.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private String id;
    private String fullName;
    private String email;
    private String haskPassWord;
    private State state;
}
