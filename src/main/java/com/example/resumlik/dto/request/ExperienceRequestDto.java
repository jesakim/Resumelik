package com.example.resumlik.dto.request;

import com.example.resumlik.model.Experience;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ExperienceRequestDto {

    @NotNull(message = "Company is required.")
    @NotBlank(message = "Company is required.")
    private String company;

    @NotNull(message = "Title is required.")
    @NotBlank(message = "Title is required.")
    private String title;

    @NotNull(message = "From date is required.")
    @Past(message = "From date must be a past date.")
    private Date fromDate;
    private Date toDate;
    private String description;

    @NotNull(message = "Resume id is required.")
    @Digits(integer = Integer.MAX_VALUE, fraction = 0, message = "Resume id is not valid.")
    private Long resumeId;
    public Experience toEntity() {
        return Experience.builder()
                .company(company)
                .title(title)
                .fromDate(fromDate)
                .toDate(toDate)
                .description(description)
                .build();
    }
}
