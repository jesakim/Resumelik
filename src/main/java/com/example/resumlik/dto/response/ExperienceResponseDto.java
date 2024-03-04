package com.example.resumlik.dto.response;

import com.example.resumlik.model.Experience;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ExperienceResponseDto {

    private Long id;
    private String company;
    private String title;
    private Date fromDate;
    private Date toDate;
    private String description;

    public ExperienceResponseDto(Experience experience) {
        this.id = experience.getId();
        this.company = experience.getCompany();
        this.title = experience.getTitle();
        this.fromDate = experience.getFromDate();
        this.toDate = experience.getToDate();
        this.description = experience.getDescription();
    }
}
