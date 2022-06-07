package vn.techmaster.banksimulator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.techmaster.banksimulator.model.Account;
import vn.techmaster.banksimulator.model.User;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,String> {
    List<Account> findAllByUser(User user);
}
