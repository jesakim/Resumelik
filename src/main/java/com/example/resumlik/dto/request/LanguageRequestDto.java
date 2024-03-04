package com.example.resumlik.dto.request;

import com.example.resumlik.model.Language;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LanguageRequestDto {

    @NotNull(message = "Name is required.")
    @NotBlank(message = "Name is required.")
    private String name;

    @NotNull(message = "Level is required.")
    @Digits(integer = 1, fraction = 0, message = "Level is not valid.")
    @Max(value = 5, message = "Level must be less than or equal to 5.")
    @Min(value = 1, message = "Level must be greater than or equal to 1.")
    private Integer level;

    @NotNull(message = "Resume id is required.")
    @Digits(integer = Integer.MAX_VALUE, fraction = 0, message = "Resume id is not valid.")
    private Long resumeId;

    public Language toEntity() {
        return Language.builder()
                .name(name)
                .level(level)
                .build();
    }

}
