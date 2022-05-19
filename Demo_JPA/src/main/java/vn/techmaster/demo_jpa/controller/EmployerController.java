package vn.techmaster.demo_jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.techmaster.demo_jpa.model.Employer;
import vn.techmaster.demo_jpa.repository.EmployerRepo;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employer")
public class EmployerController {
    @Autowired private EmployerRepo emRepo;
    @GetMapping
    public List<Employer> getAll() {
        return emRepo.findAll();
    }
    @GetMapping(value="/{id}")
    public Optional<Employer> findByID(@PathVariable("id") Long id) {
        return emRepo.findById(id);
    }

}
