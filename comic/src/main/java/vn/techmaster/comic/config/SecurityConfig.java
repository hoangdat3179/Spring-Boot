package vn.techmaster.comic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import vn.techmaster.comic.security.Role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    public static PasswordEncoder delegatePasswordEncoder(String encodingType) {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder());

        return new DelegatingPasswordEncoder(encodingType, encoders);
    }

    @Bean
    public PasswordEncoder encoder() {
        return SecurityConfig.delegatePasswordEncoder("bcrypt");
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        Collection<UserDetails> users = new ArrayList<>();
        User.UserBuilder userBuilder = User.builder().passwordEncoder(encoder()::encode);
        var tom = userBuilder.username("tom@gmail.com").password("123").roles(Role.USER).build();
        var bob = userBuilder.username("bob@gmail.com").password("123").roles(Role.USER).build();
        var alice = userBuilder.username("alice@gmail.com").password("123").roles(Role.USER).build();

        var operator = userBuilder.username("operator@gmail.com").password("123").roles(Role.OPERATOR).build();
        var boss = userBuilder.username("admin@gmail.com").password("123").roles(Role.ADMIN).build();

        users.add(tom);
        users.add(bob);
        users.add(alice);
        users.add(operator);
        users.add(boss);

        return new InMemoryUserDetailsManager(users);
    }

}
