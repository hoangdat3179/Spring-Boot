package vn.techmaster.webtruyen_project.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String username;
    private String password;
    private boolean enabled;
    private String role;
    public User() {
        this.role = "ROLE_USER";
    }
    public boolean isEnabled() {
        return enabled;
    }
}
