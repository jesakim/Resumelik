package com.example.resumlik.service;

import com.example.resumlik.dto.request.UserRequestDto;
import com.example.resumlik.dto.response.UserResponseDto;
import com.example.resumlik.model.User;
import com.example.resumlik.repository.UserRepository;
import com.example.resumlik.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public Response<UserResponseDto> save(UserRequestDto userDto) {
        User user= userRepository.save(userDto.toEntity());

        UserResponseDto userDtoResponse = new UserResponseDto(user);

        return Response.<UserResponseDto>builder()
                .message("User created successfully")
                .result(userDtoResponse)
                .build();
    }

    public Response<List<UserResponseDto>> getAllUsers() {

        List<UserResponseDto> userDtoList = userRepository.findAll().stream()
                .map(UserResponseDto::new)
                .toList();

        return Response.<List<UserResponseDto>>builder()
                .result(userDtoList)
                .build();

    }

    public Response<UserResponseDto> getById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return Response.<UserResponseDto>builder()
                    .message("User not found")
                    .build();
        }
        UserResponseDto userDto = new UserResponseDto(user);
        return Response.<UserResponseDto>builder()
                .result(userDto)
                .build();
    }
}
