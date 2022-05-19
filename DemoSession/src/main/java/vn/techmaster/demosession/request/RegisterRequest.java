package vn.techmaster.demosession.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank
        String name,
        @NotBlank(message = "Không được để trống")
        @Email(message = "Định dạng không đúng")
        String email,
        @Size(min = 5, max = 20, message = "Vui lòng nhập từ  5 đến 20 kí tự")
        String password,
        @Size(min = 5, max = 20, message = "Vui lòng nhập từ  5 đến 20 kí tự")
        String retypePassword
)
{
}
