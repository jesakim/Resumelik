package com.example.resumlik.enums;

public enum ContactType {
    PHONE,
    EMAIL,
    LINKEDIN,
    GITHUB,
    TWITTER,
    INSTAGRAM,
    FACEBOOK,
    WEBSITE,
    OTHER;

    public static ContactType fromString(String value) {
        for (ContactType type : ContactType.values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        return OTHER;
    }
}
