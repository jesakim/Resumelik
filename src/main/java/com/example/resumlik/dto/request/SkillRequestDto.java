package com.example.resumlik.dto.request;

import com.example.resumlik.enums.SkillType;
import com.example.resumlik.model.Skill;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SkillRequestDto {

    @NotNull(message = "Name is required")
    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Type is required")
    @NotBlank(message = "Type is required")
    private String type;


    @NotNull(message = "Resume id is required.")
    @Digits(integer = Integer.MAX_VALUE, fraction = 0, message = "Resume id is not valid.")
    private Long resumeId;
    public Skill toEntity() {
        Skill skill = new Skill();
        skill.setName(name);
        skill.setType(SkillType.fromString(type));
        return skill;
    }
}
