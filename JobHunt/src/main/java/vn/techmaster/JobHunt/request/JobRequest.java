package vn.techmaster.JobHunt.request;

import javax.validation.constraints.*;

import vn.techmaster.JobHunt.model.City;

public record JobRequest(String id,
                @NotBlank(message = "Emp id cannot null") String emp_id,
                @NotBlank(message = "Title cannot null") String title,
                @NotBlank(message = "description cannot null") String description,
                City city) {

}
