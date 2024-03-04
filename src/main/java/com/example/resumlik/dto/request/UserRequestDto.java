package com.example.resumlik.dto.request;

import com.example.resumlik.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class UserRequestDto {

    @NotNull(message = "Firstname is required")
    @NotBlank(message = "Firstname is required")
    private String firstName;

    @NotNull(message = "lastName is required")
    @NotBlank(message = "lastName is required")
    private String lastName;

    @NotNull(message = "Email is required")
    @NotBlank(message = "Email is required")
    @Email(message = "Email is not valid")
    private String email;

    @NotNull(message = "Password is required")
    @NotBlank(message = "Password is required")
    private String password;

    public User toEntity() {
        return User.builder()
                .firstName(this.firstName)
                .lastName(this.lastName)
                .email(this.email)
                .password(this.password)
                .build();
    }
}
