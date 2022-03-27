package vn.techmaster.jobListing.controller;

import org.springframework.web.bind.annotation.*;

import vn.techmaster.jobListing.location.location;
import vn.techmaster.jobListing.dto.jobRequest;
import vn.techmaster.jobListing.model.job;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/job")
public class jobController {
    private ConcurrentHashMap<String, job> jobs;

    public jobController() {
        jobs = new ConcurrentHashMap<String, job>();
        jobs.put("01", new job("01", "job1", "job01", location.HaNoi, 20, 40, "123abc@gmail.com"));
        jobs.put("02", new job("02", "job2", "job02", location.HaiPhong, 60, 90, "abc123@gmail.com"));
        jobs.put("03", new job("03", "job3", "job03", location.NamDinh, 100, 130, "qwert123@gmail.com"));
        jobs.put("04", new job("04", "job4", "job04", location.HaNoi, 130, 160, "qwert123@gmail.com"));
    }

    @GetMapping
    public List<job> getJobs() {
        return jobs.values().stream().toList();
    }

    @PostMapping
    public job createNewJob(@RequestBody jobRequest jobRequest) {
        String uuid = UUID.randomUUID().toString();
        job newjob = new job(uuid, jobRequest.title(), jobRequest.description(), location.HaiPhong,
                jobRequest.min_salary(), jobRequest.max_salary(), jobRequest.email_to());
        jobs.put(uuid, newjob);
        return newjob;
    }

    @GetMapping(value = "/{id}")
    public job getJobById(@PathVariable("id") String id) {
        return jobs.get(id);
    }

    @PutMapping(value = "/{id}")
    public job updateJobById(@PathVariable("id") String id, @RequestBody jobRequest jobRequest) {
        job updatejob = new job(id, jobRequest.title(), jobRequest.description(), location.HaiPhong,
                jobRequest.min_salary(), jobRequest.max_salary(), jobRequest.email_to());
        jobs.replace(id, updatejob);
        return updatejob;
    }

    @DeleteMapping(value = "/{id}")
    public job deleteJobById(@PathVariable("id") String id) {
        return jobs.remove(id);
    }

    @GetMapping(value = "/sortbylocation")
    public List<job> sortJobByLocation() {
        return jobs.values().stream()
                .sorted(Comparator.comparing(job::getLocation))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/salary/{salary}")
    public List<job> getJobBySalary(@PathVariable("salary") int salary) {
        return jobs.values().stream().filter(i -> (i.getMin_salary() <= (salary)) && (i.getMax_salary() >= (salary)))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/keyword/{keyword}")
    public List<job> getJobByKeyword(@PathVariable("keyword") String keyword) {
        return jobs.values().stream()
                .filter(i -> (i.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                        || (i.getDescription().toLowerCase().contains(keyword.toLowerCase())))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/query?location={location}&keyword={keyword}")
    public List<job> getJobByLocationAndKeyword(@RequestParam("location") location location,
            @RequestParam("keyword") String keyword) {
        return jobs.values().stream()
                .filter(i -> ((i.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                        || (i.getDescription().toLowerCase().contains(keyword.toLowerCase())))
                        && (i.getLocation().equals(location)))
                .collect(Collectors.toList());
    }
}
