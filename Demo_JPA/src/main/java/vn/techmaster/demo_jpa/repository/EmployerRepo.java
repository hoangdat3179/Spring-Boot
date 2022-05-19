package vn.techmaster.demo_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.techmaster.demo_jpa.model.Employer;

public interface EmployerRepo extends JpaRepository<Employer, Long>  {
  
}
