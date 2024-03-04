package com.example.resumlik.dto.response;

import com.example.resumlik.model.Project;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ProjectResponseDto {

    private Long id;
    private String name;
    private String title;
    private String mode;
    private Date realisedAt;
    private String description;

    public ProjectResponseDto(Project project) {
        this.id = project.getId();
        this.name = project.getName();
        this.title = project.getTitle();
        this.mode = project.getMode().name();
        this.realisedAt = project.getRealisedAt();
        this.description = project.getDescription();
    }
}
