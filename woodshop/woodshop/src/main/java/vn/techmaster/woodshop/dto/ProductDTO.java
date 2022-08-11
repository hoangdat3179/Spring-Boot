package vn.techmaster.woodshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import vn.techmaster.woodshop.entity.Status;

import javax.validation.constraints.NotBlank;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private String id;

    @NotBlank(message = "Name cannot null")
    private String name;

    private int quantity;

    private double price;

    private Status status;

    private String image;

    private MultipartFile imageMultipartFile;

    @NotBlank(message = "Description cannot null")
    private String description;

    private double discount;

    private String category_id;

    private Date created_at;

    private Date update_at;

    private Boolean isCheck = false;

}
