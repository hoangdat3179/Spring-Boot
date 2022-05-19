package vn.techmaster.demo_jpa;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vn.techmaster.demo_jpa.model.Employer;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@SpringBootApplication
public class DemoJpaApplication implements CommandLineRunner {
    @Autowired private EntityManager em;

    public static void main(String[] args) {
        SpringApplication.run(DemoJpaApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        for (int i = 0; i < 5; i++) {
            var employer = Employer.builder().name(faker.company().name())
                    .email(faker.internet().emailAddress())
                    .website("https://" + faker.internet().domainName()).build();
            em.persist(employer);
        }
        em.flush();
    }
}
