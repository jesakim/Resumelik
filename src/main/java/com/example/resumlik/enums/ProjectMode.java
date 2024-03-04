package com.example.resumlik.enums;

public enum ProjectMode {
    PERSONAL,
    GROUP,
    ACADEMIC,
    PROFESSIONAL,
    OPEN_SOURCE ,
    OTHER;

    public static ProjectMode fromString(String value) {
        for (ProjectMode type : ProjectMode.values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        return OTHER;
    }
}
