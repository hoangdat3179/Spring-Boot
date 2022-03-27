package vn.techmaster.jobListing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.techmaster.jobListing.location.location;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class job {
    private String id ;
    private String title ;
    private String description ;
    private location location ;
    private int min_salary ;
    private int max_salary ;
    private String email_to ;
    
}
