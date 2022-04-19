package vn.techmaster.job_hunt.controller;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.techmaster.job_hunt.model.City;
import vn.techmaster.job_hunt.model.Job;
import vn.techmaster.job_hunt.respository.JobRepo;

@Controller
@RequestMapping("/job")
public class JobController {
  @Autowired private JobRepo jobRepo;
  @GetMapping
  public String listAllJobs(Model model) {
    model.addAttribute("jobs", jobRepo.getAll());
    return "jobs";
  }

  @GetMapping("/add")
  public String showAddJobForm(Model model, @RequestParam("emp_id") String emp_id,City city) {
    Job job = new Job();
    job.setEmp_id(emp_id);
    job.setCity(city);
    model.addAttribute("job", job);
    model.addAttribute("cities", City.values());
    return "job_add";
  }
  @PostMapping("/add")
  public String showAddJob(Model model, @RequestParam("emp_id") String emp_id,City city) {
    Job job = new Job();
    job.setEmp_id(emp_id);
    job.setCity(city);
    model.addAttribute("job", job);
    model.addAttribute("cities", City.values());
    return "redirect:/job";
  }
}
