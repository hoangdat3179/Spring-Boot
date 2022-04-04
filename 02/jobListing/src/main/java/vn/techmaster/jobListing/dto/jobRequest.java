package vn.techmaster.jobListing.dto;

import vn.techmaster.jobListing.location.Location;

public record JobRequest(String title , String description , Location location , int min_salary , int max_salary , String email_to) {
}
