package com.project.contactmanager.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Scope;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ContactDto {

    private Long contactId;

    @NotBlank(message = "Enter valid name")
    private String name;

    @Size(min = 10, max=10, message = "Enter a 10 digit number")
    @Pattern(regexp = "[\\s]*[0-9]*[0-9]+",message="Enter Valid number")
    private String phone;

    private String email;
    private MultipartFile file;
    private String image;
}
