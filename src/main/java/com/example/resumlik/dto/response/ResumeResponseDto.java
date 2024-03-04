package com.example.resumlik.dto.response;

import com.example.resumlik.model.Resume;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class ResumeResponseDto {

    private Long id;
    private String name;
    private String firstName;
    private String lastName;
    private String picture;
    private String title;
    private Long userId;

    public ResumeResponseDto(Resume resume) {
        this.id = resume.getId();
        this.name = resume.getName();
        this.firstName = resume.getFirstName();
        this.lastName = resume.getLastName();
        this.picture = resume.getPicture();
        this.title = resume.getTitle();
        this.userId = resume.getUser().getId();
    }
}
