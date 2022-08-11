package vn.techmaster.woodshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.techmaster.woodshop.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
}
