package com.example.resumlik.dto.request;

import com.example.resumlik.enums.ProjectMode;
import com.example.resumlik.model.Project;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Setter
@Getter
public class ProjectRequestDto {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Mode is required")
    private String mode;

    private Date realisedAt;

    private String description;

    @NotNull(message = "Resume id is required.")
    @Digits(integer = Integer.MAX_VALUE, fraction = 0, message = "Resume id is not valid.")
    private Long resumeId;
    public Project toEntity() {
        return Project.builder()
                .name(name)
                .title(title)
                .mode(ProjectMode.fromString(mode))
                .realisedAt(realisedAt)
                .description(description)
                .build();
    }
}
