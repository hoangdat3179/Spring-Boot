package vn.techmaster.jobListing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.techmaster.jobListing.location.Location;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job {
    private String id ;
    private String title ;
    private String description ;
    private Location location ;
    private int min_salary ;
    private int max_salary ;
    private String email_to ;
    
}
