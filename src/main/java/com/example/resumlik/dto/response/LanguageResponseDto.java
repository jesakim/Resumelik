package com.example.resumlik.dto.response;

import com.example.resumlik.model.Language;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LanguageResponseDto {

    private Long id;
    private String name;
    private Integer level;

    public LanguageResponseDto(Language language) {
        this.id = language.getId();
        this.name = language.getName();
        this.level = language.getLevel();
    }
}
