package vn.techmaster.banksimulator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import vn.techmaster.banksimulator.model.Account;
import vn.techmaster.banksimulator.model.User;
import vn.techmaster.banksimulator.repository.AccountRepository;
import vn.techmaster.banksimulator.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BankSimulatorApplication implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;

    public static void main(String[] args) {
        SpringApplication.run(BankSimulatorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Account account1 = new Account("1","Bob1",100000L);
        Account account2 = new Account("2","Bob2",500000L);
        Account account3 = new Account("3","Bob3",20000L);
        Account account4 = new Account("4","Alice1",300000L);
        Account account5 = new Account("5","Alice2",500000L);
        Account account6= new Account("6","Alice3",10000L);
        Account account7 = new Account("7","Tom1",50000L);
        Account account8 = new Account("8","Tom2",200000L);
        Account account9 = new Account("9","Tom3",30000L);
        Account account10 = new Account("10","Sara1",500000L);





        List<Account> Bob = new ArrayList<>();
        Bob.add(account1);
        Bob.add(account2);
        Bob.add(account3);
        accountRepository.saveAll(Bob);
        User user1 = new User("1","Bob",Bob);
        userRepository.save(user1);

        List<Account> Alice = new ArrayList<>();
        Alice.add(account4);
        Alice.add(account5);
        Alice.add(account6);
        accountRepository.saveAll(Alice);
        User user2 = new User("2","Alice",Alice);
        userRepository.save(user2);

        List<Account> Tom = new ArrayList<>();
        Tom.add(account7);
        Tom.add(account8);
        Tom.add(account9);
        accountRepository.saveAll(Tom);
        User user3 = new User("3","Tom",Tom);
        userRepository.save(user3);

        List<Account> Sara = new ArrayList<>();
        Sara.add(account10);
        accountRepository.saveAll(Sara);
        User user4 = new User("4","Sara",Sara);
        userRepository.save(user4);
    }

}
