package vn.techmaster.JobHunt.request;

import javax.validation.constraints.*;

import org.springframework.web.multipart.MultipartFile;

public record EmployerRequest(String id, 
        @NotBlank(message = "Name cannot null") String name,
        @NotBlank(message = "Website cannot null") String website,
        @NotBlank(message = "Email cannot null") @Email(message = "Not valid email") String email, 
        MultipartFile logo) {
}
