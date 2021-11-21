package com.project.contactmanager.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto {

    private Long userId;

    @NotBlank(message = "Name field required")
    private String name;

    @Email(message = "Enter valid email", regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @Size(min=8, max=15, message = "Password should have min 8 and max 20 characters")
    private String password;

    private MultipartFile file;
    private String image;
}
