package vn.techmaster.demosession;

import org.junit.jupiter.api.Test;
import vn.techmaster.demosession.model.State;
import vn.techmaster.demosession.model.User;
import vn.techmaster.demosession.repository.UserRepo;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestUserRepo {
    @Test
    public void addUser(){
        UserRepo userRepo = new UserRepo();
        User user = userRepo.addUser("John","john@gmail.com","213214", State.PENDING);
        assertThat(user).isNotNull();
        System.out.println(user.getId());
        assertThat(user.getId()).isNotBlank();
    }

    @Test
    public void addUserWithPendingState(){
        UserRepo userRepo = new UserRepo();
        User user = userRepo.addUser("John","john@gmail.com","123456");
        assertThat(user).isNotNull();
        assertThat(user.getId()).isNotBlank();
        assertThat(user.getState()).isEqualTo(State.PENDING);
    }
    @Test
    public void isEmailExist(){
        UserRepo userRepo = new UserRepo();
        userRepo.addUser("John","john@gmail.com","213214");
        userRepo.addUser("Thái","thai@gmail.com","324234");
        userRepo.addUser("Cường","cuong@gmail.com","6547547");

        assertThat(userRepo.isEmailExist("john@gmail.com")).isTrue();
        assertThat(userRepo.isEmailExist("thai@gmail.com")).isTrue();
        assertThat(userRepo.isEmailExist("cuong@gmail.com")).isTrue();
        assertThat(userRepo.isEmailExist("cuong85@gmail.com")).isFalse();

    }

    @Test
    public void findByEmail(){
        UserRepo userRepo = new UserRepo();
        userRepo.addUser("John","john@gmail.com","213214");
        userRepo.addUser("Thái","thai@gmail.com","324234");
        userRepo.addUser("Cường","cuong@gmail.com","6547547");

        assertThat(userRepo.findByEmail("john@gmail.com")).isPresent();
        assertThat(userRepo.findByEmail("john@gmail.com")).isPresent();
        assertThat(userRepo.findByEmail("john@gmail.com")).isNotPresent();
    }
}
