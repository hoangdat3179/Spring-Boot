package vn.techmaster.woodshop.request;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

public record ProductRequest(

        String id ,

        @NotBlank(message = "Name cannot null") String name,

        String category_id ,

         int quantity,

         double price,

        double discount,

        MultipartFile image,

        @NotBlank(message = "Description cannot null") String description)

{
}
