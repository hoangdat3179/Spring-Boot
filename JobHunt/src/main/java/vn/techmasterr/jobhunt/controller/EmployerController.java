package vn.techmasterr.jobhunt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import vn.techmasterr.jobhunt.model.Employer;
import vn.techmasterr.jobhunt.repository.EmployerRepository;

@Controller
@RequestMapping("/employer")
public class EmployerController {
    @Autowired
    private EmployerRepository repo;

    @GetMapping("/list")
    public String employers(Model model) {
        model.addAttribute("employers", repo.getEmployer());
        return "/employer/list";
    }

    @RequestMapping("/add")
    public String insertCustomer(Model model) {
        model.addAttribute("customer", );
        return "/employer/add";
    }
}