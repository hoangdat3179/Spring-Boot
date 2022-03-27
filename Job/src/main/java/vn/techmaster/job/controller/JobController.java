package vn.techmaster.job.controller;

import org.springframework.web.bind.annotation.*;

import vn.techmaster.job.Location;
import vn.techmaster.job.dto.JobRequest;
import vn.techmaster.job.model.Job;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/job")
public class JobController {
    private ConcurrentHashMap<String, Job> jobs;

    public JobController() {
        jobs = new ConcurrentHashMap<String, Job>();
        jobs.put("01", new Job("01", "Job1", "Job1", Location.HaNoi, 20, 40, "123abc@gmail.com"));
        jobs.put("02", new Job("02", "Job2", "Job2", Location.HaiPhong, 60, 90, "abc123@gmail.com"));
        jobs.put("03", new Job("03", "Job3", "Job3", Location.NamDinh, 100, 130, "qwert123@gmail.com"));
        jobs.put("04", new Job("04", "Job4", "Job4", Location.HaNoi, 130, 160, "qwert123@gmail.com"));
    }

    @GetMapping
    public List<Job> getBooks() {
        return jobs.values().stream().toList();
    }

    @PostMapping
    public Job createNewBook(@RequestBody JobRequest jobRequest) {
        String uuid = UUID.randomUUID().toString();
        Job newJob = new Job(uuid, jobRequest.title(), jobRequest.description(), Location.HaiPhong,
                jobRequest.min_salary(), jobRequest.max_salary(), jobRequest.email_to());
        jobs.put(uuid, newJob);
        return newJob;
    }

    @GetMapping(value = "/{id}")
    public Job getJobById(@PathVariable("id") String id) {
        return jobs.get(id);
    }

    @PutMapping(value = "/{id}")
    public Job updateJobById(@PathVariable("id") String id, @RequestBody JobRequest jobRequest) {
        Job updateJob = new Job(id, jobRequest.title(), jobRequest.description(), Location.HaiPhong,
                jobRequest.min_salary(), jobRequest.max_salary(), jobRequest.email_to());
        jobs.replace(id, updateJob);
        return updateJob;
    }

    @DeleteMapping(value = "/{id}")
    public Job deleteBookById(@PathVariable("id") String id) {
        return jobs.remove(id);
    }

    @GetMapping(value = "/sortbylocation")
    public List<Job> sortJobByLocation() {
        return jobs.values().stream()
                .sorted(Comparator.comparing(Job::getLocation))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/salary/{salary}")
    public List<Job> getJobBySalary(@PathVariable("salary") int salary) {
        return jobs.values().stream().filter(i -> (i.getMin_salary() <= (salary)) && (i.getMax_salary() >= (salary)))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/keyword/{keyword}")
    public List<Job> getListByKeyword(@PathVariable("keyword") String keyword) {
        return jobs.values().stream()
                .filter(i -> (i.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                        || (i.getDescription().toLowerCase().contains(keyword.toLowerCase())))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/query?location={location}&keyword={keyword}")
    public List<Job> getListByLocationAndKeyword(@RequestParam("location") String location,
            @RequestParam("keyword") String keyword) {
        return jobs.values().stream()
                .filter(i -> ((i.getTitle().contains(keyword)) || (i.getDescription().contains(keyword)))
                        && (i.getLocation().equals(location)))
                .collect(Collectors.toList());
    }
}
