package vn.techmaster.demosession;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.techmaster.demosession.security.Hashing;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class TestHashing {
    @Autowired private Hashing hash;

    @Test
    public void hashPassword(){
        var passwords = List.of("abc" , "qwert","ox-123");
        for(String password : passwords){
            String hashed_pass = hash.hashPassword(password);
            assertThat(hashed_pass).isNotNull();
        }
    }

    @Test void validatePassword(){
        var passwords = List.of("abc12312_+P" , "qwert","ox-123","(#$GJDSMS");
        for(String password : passwords){
            String hashed_pass = hash.hashPassword(password);
            assertThat(hash.validatePassword(password,hashed_pass)).isTrue();
        }
    }
}
