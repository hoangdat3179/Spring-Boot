package vn.techmaster.storyreadingwebsite.Service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import vn.techmaster.storyreadingwebsite.entity.User;

@Service
public class UserService {

    public User getLogin(Authentication authentication){
        if (authentication == null) return null;
        User user = null;
        Object principal = authentication.getPrincipal();
        if (principal instanceof CustomUserDetails){
            user = ((CustomUserDetails)principal).getUser();
        }
        return user;
    }
}
