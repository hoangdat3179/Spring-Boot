package vn.techmaster.storyreadingwebsite;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseLoader {

//    private UserRepository repo;
//
//    public DatabaseLoader(UserRepository repo) {
//        this.repo = repo;
//    }
//
//    @Bean
//    public CommandLineRunner initializeDatabase() {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        return args -> {
//            User user1 = new User("admin@gmail.com", encoder.encode("admin"), Role.ADMIN);
//            User user2 = new User("user@gmail.com", encoder.encode("123"), Role.USER);
//
//            repo.saveAll(List.of(user1,user2));
//
//            System.out.println("Database initialized");
//        };
//    }

}
