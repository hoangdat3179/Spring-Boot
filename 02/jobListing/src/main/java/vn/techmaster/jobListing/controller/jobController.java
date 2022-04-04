package vn.techmaster.jobListing.controller;

import org.springframework.web.bind.annotation.*;

import vn.techmaster.jobListing.location.Location;
import vn.techmaster.jobListing.dto.JobRequest;
import vn.techmaster.jobListing.model.Job;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Job")
public class JobController {
    private ConcurrentHashMap<String, Job> Jobs;

    public JobController() {
        Jobs = new ConcurrentHashMap<String, Job>();
        Jobs.put("01", new Job("01", "Job1", "Job01", Location.HaNoi, 20, 40, "123abc@gmail.com"));
        Jobs.put("02", new Job("02", "Job2", "Job02", Location.HaiPhong, 60, 90, "abc123@gmail.com"));
        Jobs.put("03", new Job("03", "Job3", "Job03", Location.NamDinh, 100, 130, "qwert123@gmail.com"));
        Jobs.put("04", new Job("04", "Job4", "Job04", Location.HaNoi, 130, 160, "qwert123@gmail.com"));
    }

    @GetMapping
    public List<Job> getJobs() {
        return Jobs.values().stream().toList();
    }

    @PostMapping
    public Job createNewJob(@RequestBody JobRequest JobRequest) {
        String uuid = UUID.randomUUID().toString();
        Job newJob = new Job(uuid, JobRequest.title(), JobRequest.description(), Location.HaiPhong,
                JobRequest.min_salary(), JobRequest.max_salary(), JobRequest.email_to());
        Jobs.put(uuid, newJob);
        return newJob;
    }

    @GetMapping(value = "/{id}")
    public Job getJobById(@PathVariable("id") String id) {
        return Jobs.get(id);
    }

    @PutMapping(value = "/{id}")
    public Job updateJobById(@PathVariable("id") String id, @RequestBody JobRequest JobRequest) {
        Job updateJob = new Job(id, JobRequest.title(), JobRequest.description(), Location.HaiPhong,
                JobRequest.min_salary(), JobRequest.max_salary(), JobRequest.email_to());
        Jobs.replace(id, updateJob);
        return updateJob;
    }

    @DeleteMapping(value = "/{id}")
    public Job deleteJobById(@PathVariable("id") String id) {
        return Jobs.remove(id);
    }

    @GetMapping(value = "/sortbyLocation")
    public List<Job> sortJobByLocation() {
        return Jobs.values().stream()
                .sorted(Comparator.comparing(Job::getLocation))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/salary/{salary}")
    public List<Job> getJobBySalary(@PathVariable("salary") int salary) {
        return Jobs.values().stream().filter(i -> (i.getMin_salary() <= (salary)) && (i.getMax_salary() >= (salary)))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/keyword/{keyword}")
    public List<Job> getJobByKeyword(@PathVariable("keyword") String keyword) {
        return Jobs.values().stream()
                .filter(i -> (i.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                        || (i.getDescription().toLowerCase().contains(keyword.toLowerCase())))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/query?Location={Location}&keyword={keyword}")
    public List<Job> getJobByLocationAndKeyword(@RequestParam("Location") Location Location,
            @RequestParam("keyword") String keyword) {
        return Jobs.values().stream()
                .filter(i -> ((i.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                        || (i.getDescription().toLowerCase().contains(keyword.toLowerCase())))
                        && (i.getLocation().equals(Location)))
                .collect(Collectors.toList());
    }
}
