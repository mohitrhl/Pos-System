package com.mohit.controller;

import com.mohit.configuration.JwtProvider;
import com.mohit.exception.UserException;
import com.mohit.payload.dto.UserDTO;
import com.mohit.payload.request.ForgotPasswordRequest;
import com.mohit.payload.request.LoginDto;
import com.mohit.payload.request.ResetPasswordRequest;
import com.mohit.payload.response.ApiResponse;
import com.mohit.payload.response.ApiResponseBody;
import com.mohit.payload.response.AuthResponse;
import com.mohit.repository.UserRepository;
import com.mohit.service.AuthService;
import com.mohit.service.UserService;
import com.mohit.service.impl.CustomUserImplementation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomUserImplementation customUserImplementation;
    private final UserService userService;
    private final AuthService authService;


    @PostMapping("/signup")
    public ResponseEntity<ApiResponseBody<AuthResponse>> signupHandler(
            @RequestBody @Valid UserDTO req) throws UserException {


        AuthResponse response=authService.signup(req);


        return ResponseEntity.ok(new ApiResponseBody<>(true,
                "User created successfully", response));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponseBody<AuthResponse>> loginHandler(
            @RequestBody LoginDto req) throws UserException {

        AuthResponse response=authService.login(req.getEmail(), req.getPassword());

        return ResponseEntity.ok(new ApiResponseBody<>(
                true,
                "User logged in successfully",
                response));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<ApiResponse> forgotPassword(
            @RequestBody ForgotPasswordRequest request
    ) throws UserException {

        authService.createPasswordResetToken(request.getEmail());

        ApiResponse res= new ApiResponse(
                "A Reset link was sent to your email."
        );
        return ResponseEntity.ok(res);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse> resetPassword(
            @RequestBody ResetPasswordRequest request) {
        authService.resetPassword(request.getToken(), request.getPassword());
        ApiResponse res= new ApiResponse(
                "Password reset successful"
        );
        return ResponseEntity.ok(res);
    }

}
