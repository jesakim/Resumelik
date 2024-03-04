package com.example.resumlik.dto.request;

import com.example.resumlik.model.Address;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressRequestDto {

    private String street;
    private String additionalStreet;
    private String city;

    @NotNull(message = "Country is required")
    @NotBlank(message = "Country is required")
    private String country;
    //validate that resumeId is not null and is numeric

    @NotNull(message = "ResumeId is required")
    @Digits(integer = Integer.MAX_VALUE, fraction = 0, message = "ResumeId is not valid")
    private Long resumeId;

    public Address toEntity() {
        return Address.builder()
                .street(street)
                .additionalStreet(additionalStreet)
                .city(city)
                .country(country)
                .build();
    }
}
