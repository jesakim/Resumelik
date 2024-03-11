package com.example.resumlik.dto.response;

import com.example.resumlik.model.Resume;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FullResumeResponseDto {
    private Long id;
    private String name;
    private String firstName;
    private String lastName;
    private String picture;
    private String title;
    private Long userId;

    private AddressResponseDto[] addresses;
    private CertificateResponseDto[] certificates;
    private ContactResponseDto[] contacts;
    private EducationResponseDto[] educations;
    private ExperienceResponseDto[] experiences;
    private HobbyResponseDto[] hobbies;
    private LanguageResponseDto[] languages;
    private ProjectResponseDto[] projects;
    private SkillResponseDto[] skills;
    private ViewResponseDto[] views;

    public FullResumeResponseDto(Resume resume) {
        this.id = resume.getId();
        this.name = resume.getName();
        this.firstName = resume.getFirstName();
        this.lastName = resume.getLastName();
        this.picture = resume.getPicture();
        this.title = resume.getTitle();
        this.userId = resume.getUser().getId();
    }


}
