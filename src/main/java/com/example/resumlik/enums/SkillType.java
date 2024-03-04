package com.example.resumlik.enums;

public enum SkillType {
    PROGRAMMING_LANGUAGE,
    FRAMEWORK,
    TECHNOLOGY,
    DATABASE,
    PLATFORM,
    SOFTSKILL,
    HARDSKILL,
    OTHER;


    public static SkillType fromString(String value) {
        for (SkillType type : SkillType.values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        return OTHER;
    }
}
