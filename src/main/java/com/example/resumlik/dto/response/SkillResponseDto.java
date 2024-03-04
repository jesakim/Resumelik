package com.example.resumlik.dto.response;

import com.example.resumlik.model.Skill;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SkillResponseDto {

    private Long id;
    private String name;
    private String type;

    public SkillResponseDto(Skill skill) {
        this.id = skill.getId();
        this.name = skill.getName();
        this.type = skill.getType().name();
    }
}
