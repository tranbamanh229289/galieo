package com.manhtb.authservice.controller;

import com.manhtb.authservice.dto.request.LoginRequest;
import com.manhtb.authservice.dto.request.RegisterRequest;
import com.manhtb.authservice.dto.response.BaseResponse;
import com.manhtb.authservice.dto.response.LoginResponse;
import com.manhtb.authservice.service.impl.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/auths")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping(value = "/login")
    ResponseEntity<BaseResponse<LoginResponse>> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(loginRequest));
    }

    @PostMapping(value = "/register")
    ResponseEntity<BaseResponse> register(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.status(HttpStatus.OK).body(authService.register(registerRequest));
    }
}
