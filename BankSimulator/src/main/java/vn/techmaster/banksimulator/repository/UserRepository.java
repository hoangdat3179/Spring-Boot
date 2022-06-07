package vn.techmaster.banksimulator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.techmaster.banksimulator.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
}
