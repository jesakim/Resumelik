package com.example.resumlik.service;

import com.example.resumlik.dto.request.LoginDto;
import com.example.resumlik.dto.request.RegisterDto;
import com.example.resumlik.dto.response.AuthResponseDto;
import com.example.resumlik.dto.response.UserResponseDto;
import com.example.resumlik.model.Token;
import com.example.resumlik.model.User;
import com.example.resumlik.repository.TokenRepository;
import com.example.resumlik.repository.UserRepository;
import com.example.resumlik.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public Response<AuthResponseDto> register(RegisterDto registerDto) {
        userRepository.findByEmail(registerDto.email()).ifPresent(user -> {
            throw new IllegalArgumentException("User with email " + registerDto.email() + " already exists");
        });
        var user = User.builder()
                .firstName(registerDto.firstName())
                .lastName(registerDto.lastName())
                .email(registerDto.email())
                .password(passwordEncoder.encode(registerDto.password()))
                .build();
        User savedUser = userRepository.save(user);
        String savedToken = jwtService.generateToken(savedUser);
        var token = Token.builder()
                .token(savedToken)
                .user(savedUser)
                .isExpired(false)
                .isRevoked(false)
                .build();
        tokenRepository.save(token);
        return Response.<AuthResponseDto>builder()
                .result(new AuthResponseDto(savedToken, new UserResponseDto(savedUser)))
                .message("User created successfully")
                .build();
    }

    public Response<AuthResponseDto> login(LoginDto loginDto) {
        var user = userRepository.findByEmail(loginDto.email())
                .orElseThrow(() -> new IllegalArgumentException("User with email " + loginDto.email() + " not found"));
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.email(),
                        loginDto.password()
                )
        );
        revokeAllUserTokens(user);
        String savedToken = jwtService.generateToken(user);
        var token = Token.builder()
                .token(savedToken)
                .user(user)
                .isExpired(false)
                .isRevoked(false)
                .build();
        tokenRepository.save(token);
        return Response.<AuthResponseDto>builder()
                .result(new AuthResponseDto(savedToken, new UserResponseDto(user)))
                .message("User logged in successfully")
                .build();
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}
