package com.mohit.controller;

import com.mohit.exception.UserException;
import com.mohit.mapper.UserMapper;
import com.mohit.modal.User;
import com.mohit.payload.dto.UserDTO;
import com.mohit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/api/users/profile")
    public ResponseEntity<UserDTO> getUserProfileFromJwtHandler(
            @RequestHeader("Authorization") String jwt) throws UserException {
        User user = userService.getUserFromJwtToken(jwt);
        UserDTO userDTO= UserMapper.toDTO(user);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("/users/list")
    public ResponseEntity<List<User>> getUsersListHandler(
            @RequestHeader("Authorization") String jwt) throws UserException {
        List<User> users = userService.getUsers();

        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDTO> getUserByIdHandler(@PathVariable Long userId) throws UserException {
        User user = userService.getUserById(userId);
        UserDTO userDTO=UserMapper.toDTO(user);

        return new ResponseEntity<>(userDTO,HttpStatus.OK);
    }

}
