package com.mohit.service;

import com.mohit.exception.UserException;
import com.mohit.payload.dto.UserDTO;
import com.mohit.payload.response.AuthResponse;

public interface AuthService {
    AuthResponse login(String username, String password) throws UserException;
    AuthResponse signup(UserDTO req) throws UserException;

    void createPasswordResetToken(String email) throws UserException;
    void resetPassword(String token, String newPassword);
}

