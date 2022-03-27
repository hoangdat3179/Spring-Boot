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
        jobs.put("01", new Job("01", "dev spring", "Spring", Location.HaNoi, 2, 4, "jtfkf@gmail.com"));
        jobs.put("02", new Job("02", "dev java", "Java", Location.HaiPhong, 6, 9, "jtfj@gmail.com"));
        jobs.put("03", new Job("03", "dev c++", "C++", Location.NamDinh, 10, 13, "kghkgy@gmail.com"));
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
        // books.put(id, updateBook);
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
}
