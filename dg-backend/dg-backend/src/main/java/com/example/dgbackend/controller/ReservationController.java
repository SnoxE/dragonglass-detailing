package com.example.dgbackend.controller;

import com.example.dgbackend.common.ResponseDto;
import com.example.dgbackend.common.dto.ContentDto;
import com.example.dgbackend.database.reservations.dto.AddReservationDto;
import com.example.dgbackend.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/daily-hours")
    public Map<LocalDate, List<LocalTime>> getAvailableDailyHours(
            @RequestParam("length_hours") int lengthHours,
            @RequestParam("length_minutes") int lengthMinutes) {
        return reservationService.getAvailableSlots(lengthHours, lengthMinutes);
    }

    @PostMapping("/{userId}/add-reservation")
    public ResponseEntity<ResponseDto> addReservation(
            @PathVariable("userId") String userId,
            @RequestBody AddReservationDto addReservationDto) {
        reservationService.addReservation(
                Integer.parseInt(userId),
                addReservationDto.serviceId(),
                addReservationDto.carId(),
                addReservationDto.startAtDate(),
                addReservationDto.startAtTime(),
                addReservationDto.length());

    return ResponseEntity.ok().build();
    }

    @DeleteMapping("{userId}/delete-reservation/{reservationId}")
    public ResponseEntity<ResponseDto> deleteReservation(
            @PathVariable("userId") String userId,
            @PathVariable("reservationId") String reservationId) {
        reservationService.deleteReservation(Integer.parseInt(userId), Integer.parseInt(reservationId));
        return ResponseEntity.ok().build();
    }
}
