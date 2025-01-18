package com.manhtb.authservice.service.impl;

import com.manhtb.authservice.dto.request.LoginRequest;
import com.manhtb.authservice.dto.request.RegisterRequest;
import com.manhtb.authservice.dto.response.BaseResponse;
import com.manhtb.authservice.dto.response.LoginResponse;
import com.manhtb.authservice.entity.User;
import com.manhtb.authservice.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;

    private final IUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public BaseResponse<LoginResponse> login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        User user = userRepository.findUserByUsername(request.getUsername());
        String jwt = jwtService.generateToken(user);
        return BaseResponse.<LoginResponse>builder()
                .message("login success !")
                .code(200)
                .data(LoginResponse.builder().token(jwt).build())
                .build();
    }

    public BaseResponse register(RegisterRequest request) {
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .age(request.getAge())
                .gender(request.getGender())
                .build();
        user.setCreateAt(Instant.now());
        userRepository.save(user);

        return BaseResponse.builder()
                .code(200)
                .message("Register success !")
                .data("")
                .build();

    }
}
