package inforce.testtask.dto.mapper.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequestDto {
    @NotBlank(message = "Please write your name please.")
    @Size(min = 2, message = "Name length must be minimum 2 characters")
    private String name;
    @NotBlank(message = "Please write your email address.")
    @Email(message = "Invalid email address")
    private String email;
    @NotBlank
    @Size(min = 8, message = "Password must be minimum 8 characters.")
    private String password;
}
