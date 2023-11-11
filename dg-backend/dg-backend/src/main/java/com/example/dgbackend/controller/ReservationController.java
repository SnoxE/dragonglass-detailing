package com.example.dgbackend.controller;

import com.example.dgbackend.common.ResponseDto;
import com.example.dgbackend.database.reservations.dto.AddReservationDto;
import com.example.dgbackend.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/{userId}/addReservation")
    public ResponseEntity<ResponseDto> addReservation(
            @PathVariable("userId") String userId,
            @RequestBody AddReservationDto addReservationDto) {
        reservationService.addReservation(
                Integer.parseInt(userId),
                addReservationDto.serviceId(),
                addReservationDto.carId(),
                Timestamp.valueOf(addReservationDto.startAt()));

        return ResponseEntity.ok().build();
    }

}
