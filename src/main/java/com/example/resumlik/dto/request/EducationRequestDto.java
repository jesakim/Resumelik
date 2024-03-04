package com.example.resumlik.dto.request;

import com.example.resumlik.model.Education;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class EducationRequestDto {

    @NotNull(message = "Establishment is required.")
    @NotBlank(message = "Establishment is required.")
    private String establishment;

    @NotNull(message = "Degree is required.")
    @NotBlank(message = "Degree is required.")
    private String degree;

    @NotNull(message = "From date is required.")
    @Past(message = "From date must be a past date.")
    private Date fromDate;

    private Date toDate;

    @NotNull(message = "Resume id is required.")
    @Digits(integer = Integer.MAX_VALUE, fraction = 0, message = "Resume id is not valid.")
    private Long resumeId;

    public Education toEntity() {
        return Education.builder()
                .establishment(establishment)
                .degree(degree)
                .fromDate(fromDate)
                .toDate(toDate)
                .build();
    }
}
