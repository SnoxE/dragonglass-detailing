package com.example.dgbackend.controller;

import com.example.dgbackend.common.ResponseDto;
import com.example.dgbackend.common.dto.ContentDto;
import com.example.dgbackend.database.car.CarDto;
import com.example.dgbackend.database.reservations.dto.ReservationDto;
import com.example.dgbackend.database.user.dto.UserDto;
import com.example.dgbackend.service.CarService;
import com.example.dgbackend.service.ReservationService;
import com.example.dgbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    CarService carService;
    @Autowired
    ReservationService reservationService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> registerUser(@RequestBody UserDto userDto) {
        UserDto user = userService.registerUser(
                userDto.firstName(),
                userDto.lastName(),
                userDto.email(),
                userDto.phoneNumber(),
                userDto.password(),
                userDto.role());

        return ResponseEntity.ok().build();
    }

    @GetMapping("/user")
    public UserDto getLoggedUser(Principal principal) {
        return userService.getUserByEmail(principal.getName());
    }

    @GetMapping("/email")
    public int getUserCountByEmail(@RequestParam("email") String email) {
        return userService.getUserCountByEmail(email);
    }

    @PostMapping("/{userId}/addCar")
    public ResponseEntity<ResponseDto> addCar(
            @PathVariable("userId") String userId,
            @RequestBody CarDto carDto) {
    carService.addCar(
            Integer.parseInt(userId),
            carDto.make(),
            carDto.model(),
            carDto.productionYear(),
            carDto.size(),
            carDto.colour());

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/cars")
    public ContentDto<CarDto> getUserCars(@PathVariable("userId") String userId) {
        return carService.getUserCars(userId);
    }

    @GetMapping("/{userId}/reservations")
    public ContentDto<ReservationDto> getUserReservations(@PathVariable("userId") String userId) {
        return reservationService.getUserReservations(userId);
    }
}
