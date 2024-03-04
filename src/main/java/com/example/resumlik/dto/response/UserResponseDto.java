package com.example.resumlik.dto.response;

import com.example.resumlik.model.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserResponseDto {

    private String firstName;
    private String lastName;
    private String email;


    public UserResponseDto(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
    }
}
