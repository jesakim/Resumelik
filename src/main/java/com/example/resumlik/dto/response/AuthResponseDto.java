package com.example.resumlik.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthResponseDto {

    private String token;
    private UserResponseDto user;

    public AuthResponseDto(String token, UserResponseDto user) {
        this.token = token;
        this.user = user;
    }
}
