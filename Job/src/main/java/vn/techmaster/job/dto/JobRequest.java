package vn.techmaster.job.dto;

import vn.techmaster.job.Location;

public record JobRequest(String title , String description , Location location , int min_salary , int max_salary , String email_to) {
}
