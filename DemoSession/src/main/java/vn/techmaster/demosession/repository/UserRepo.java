package vn.techmaster.demosession.repository;

import org.springframework.stereotype.Repository;
import vn.techmaster.demosession.model.State;
import vn.techmaster.demosession.model.User;
import vn.techmaster.demosession.security.Hashing;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepo {
    private Hashing hashing;
    private ConcurrentHashMap<String , User> users = new ConcurrentHashMap<>();
    public User addUser(String fullname , String email, String hashed_password){
        return addUser(fullname, email, hashed_password,State.PENDING);
    }
    public User addUser(String fullname , String email, String hashed_password, State state){
        String id = UUID.randomUUID().toString();
        User user = User.builder().fullname(fullname).email(email)
                .hashed_password(hashed_password).state(state).build();
        users.put(id,user);
        return user;
    }

    public boolean isEmailExist(String email){
        return users.values().stream().filter(user -> user.getEmail().equalsIgnoreCase(email)).count() > 0;
    }
    public Optional<User> findByEmail(String email){
        return users.values().stream().filter(user -> user.getEmail().equalsIgnoreCase(email)).findFirst();
    }

}
