package vn.techmaster.jobListing.dto;

import vn.techmaster.jobListing.location.location;

public record jobRequest(String title , String description , location location , int min_salary , int max_salary , String email_to) {
}
