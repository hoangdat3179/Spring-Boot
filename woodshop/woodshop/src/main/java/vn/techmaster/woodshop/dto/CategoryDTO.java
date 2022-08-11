package vn.techmaster.woodshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO implements Serializable {

    private String id ;

    @NotBlank(message = "Name cannot null")
    private String name ;

    @NotBlank(message = "Name cannot null")
    private String code ;

    @NotEmpty
    private String status;

    private Date created_at;

    private Date updated_at;

    private Boolean isCheck = false ;
}
