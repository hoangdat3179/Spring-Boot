package vn.techmaster.JobHunt.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.techmaster.JobHunt.model.City;
import vn.techmaster.JobHunt.model.Job;
import vn.techmaster.JobHunt.request.EmployerRequest;
import vn.techmaster.JobHunt.request.JobRequest;
import vn.techmaster.JobHunt.respository.EmployerRepo;
import vn.techmaster.JobHunt.respository.JobRepo;

@Controller
@RequestMapping("/job")
public class JobController {
    @Autowired
    private JobRepo jobRepo;
    @Autowired
    private EmployerRepo employerRepo;

    @GetMapping
    public String listAllJobs(Model model) {
        model.addAttribute("jobs", jobRepo.getAll());
        return "jobs";
    }

    @GetMapping(value = "/{id}")
    public String showJobDetailById(Model model, @PathVariable String id) {
        model.addAttribute("job", jobRepo.findById(id));
        return "job";
    }

    @GetMapping(value = "/add")
    public String addJobForm(Model model) {
        model.addAttribute("employers", employerRepo.getAll());
        model.addAttribute("job", new Job());
        model.addAttribute("citys", City.values());
        return "job_add";
    }

    @PostMapping("/add")
    public String addJob(@ModelAttribute JobRequest jobRequest, BindingResult bindingResult, Model model) {
      if (! bindingResult.hasErrors()) {
        System.out.println(jobRequest);
      }

      jobRepo.addJob(Job.builder().title(jobRequest.title())
      .description(jobRequest.description()).build());
      model.addAttribute("employers", employerRepo.getAll());
      model.addAttribute("citys", City.values());
      model.addAttribute("jobRequest", jobRequest);
      return "redirect:/job";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteJobById(@PathVariable String id, Model model) {
        jobRepo.deleteById(id);
        model.addAttribute("jobs", jobRepo.findById(id));
        return "job_delete";
    }

    @GetMapping(value = "/update/{id}")
    public String updateJobById(@PathVariable String id, Model model,Job job) {
        jobRepo.update(job);
        model.addAttribute("jobs", jobRepo.findById(id));
        return "job_update";
    }
}
