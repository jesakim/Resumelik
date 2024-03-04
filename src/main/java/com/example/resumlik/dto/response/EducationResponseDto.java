package com.example.resumlik.dto.response;

import com.example.resumlik.model.Education;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class EducationResponseDto {

    private Long id;
    private String establishment;
    private String degree;
    private Date fromDate;
    private Date toDate;

    public EducationResponseDto(Education education) {
        this.id = education.getId();
        this.establishment = education.getEstablishment();
        this.degree = education.getDegree();
        this.fromDate = education.getFromDate();
        this.toDate = education.getToDate();
    }
}
