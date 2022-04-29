package vn.techmaster.demosession.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vn.techmaster.demosession.exception.UserException;
import vn.techmaster.demosession.model.State;
import vn.techmaster.demosession.model.User;
import vn.techmaster.demosession.repository.UserRepo;
import vn.techmaster.demosession.security.Hashing;
import java.util.Optional;

@Service
@AllArgsConstructor

public class UserServiceInMemory implements UserService{
    private UserRepo userRepo;
    private Hashing hashing;


    @Override
    public User login(String email, String password){
        Optional<User> o_user = userRepo.findByEmail(email);
        //Nếu user không tồn tại thì báo lỗi
        if (!o_user.isPresent()) {
            throw new UserException("User is not found");
        }

        User user = o_user.get();
        //User muốn login phải có trạng thái Active
        if (user.getState() != State.ACTIVE) {
            throw new UserException("User is not activated");
        }

        //Kiểm tra password
        if (hashing.validatePassword(password, user.getHashed_password())) {
            return user;
        } else {
            throw new UserException("Password is incorrect");
        }
    }


    @Override
    public boolean logout(String email) {
        return false;
    }

    @Override
    public User addUser(String fullname,String email,String password){
        return userRepo.addUser(fullname,email, hashing.hashPassword(password));
    }

    @Override
    public User addUserThenAutoActivate(String fullname, String email, String password) {
        return userRepo.addUser(fullname,email, hashing.hashPassword(password),State.ACTIVE);
    }

    @Override
    public Boolean activateUser(String activation_code) {
        return null;
    }

    @Override
    public Boolean updatePassword(String email, String password) {
        return null;
    }

    @Override
    public Boolean updateEmail(String email, String newEmail) {
        return null;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public User findById(String id) {
        return null;
    }
}
