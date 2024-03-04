package com.example.resumlik.dto.request;

import com.example.resumlik.model.Hobby;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Setter
@Getter
public class HobbyRequestDto {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Resume id is required.")
    @Digits(integer = Integer.MAX_VALUE, fraction = 0, message = "Resume id is not valid.")
    private Long resumeId;

    public Hobby toEntity() {
        return Hobby.builder()
                .name(name)
                .build();
    }
}
