package com.example.dgbackend.controller;

import com.example.dgbackend.common.ResponseDto;
import com.example.dgbackend.database.user.dto.UserDto;
import com.example.dgbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> registerUser(@RequestBody UserDto userDto)
    {
        UserDto user = userService.registerUser(
                userDto.firstName(),
                userDto.lastName(),
                userDto.email(),
                userDto.password());

        return ResponseEntity.ok().build();
    }
}
