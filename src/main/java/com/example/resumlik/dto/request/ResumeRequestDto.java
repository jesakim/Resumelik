package com.example.resumlik.dto.request;

import com.example.resumlik.model.Resume;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class ResumeRequestDto {

    @NotBlank(message = "Name is required")
    @Length(min = 10, max = 50, message = "Name must be between 10 and 50 characters")
    private String name;
    @NotBlank(message = "Firstname is required")
    private String firstName;

    @NotBlank(message = "Lastname is required")
    private String lastName;

    @NotBlank(message = "Picture is required")
    private String picture;

    @NotBlank(message = "Title is required")
    private String title;


    public Resume toEntity() {
        return Resume.builder()
                .name(name)
                .firstName(firstName)
                .lastName(lastName)
                .picture(picture)
                .title(title)
                .build();
    }
}
