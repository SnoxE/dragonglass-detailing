package dgbackend.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import dgbackend.common.ResponseDto;
import dgbackend.common.dto.ContentDto;
import dgbackend.database.reservations.dto.AddReservationDto;
import dgbackend.database.reservations.dto.ReservationDto;
import dgbackend.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
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

    @GetMapping("/calendar")
    public ContentDto<ReservationDto> getCalendarReservations(
            @RequestParam(value = "after", required = false) String after,
            @RequestParam(value = "before", required = false) String before) {
        return reservationService.getCalendarReservations(after, before);
    }
}
