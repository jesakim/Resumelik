package com.example.resumlik.dto.response;

import com.example.resumlik.model.Hobby;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HobbyResponseDto {

    private Long id;
    private String name;

    public HobbyResponseDto(Hobby hobby) {
        this.id = hobby.getId();
        this.name = hobby.getName();
    }
}
