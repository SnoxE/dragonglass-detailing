package com.example.dgbackend.service;

import com.example.dgbackend.common.dto.ContentDto;
import com.example.dgbackend.database.car.CarDto;
import com.example.dgbackend.database.car.sql.CarSqlRow;
import com.example.dgbackend.database.reservations.dto.ReservationDto;
import com.example.dgbackend.database.reservations.dto.ReservationStartEndTimes;
import com.example.dgbackend.database.reservations.sql.AddReservationSqlService;
import com.example.dgbackend.database.reservations.sql.ReservationSqlRow;
import com.example.dgbackend.database.reservations.sql.ReservationSqlService;
import com.example.dgbackend.database.reservations.sql.ReservationStartEndTimesSqlRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ReservationService {

    @Autowired
    private final AddReservationSqlService addReservationSqlService;
    @Autowired
    private final ReservationSqlService reservationSqlService;

    public ReservationService(AddReservationSqlService addReservationSqlService, ReservationSqlService reservationSqlService) {
        this.addReservationSqlService = addReservationSqlService;
        this.reservationSqlService = reservationSqlService;
    }

    public Integer addReservation(
            int userId,
            int serviceId,
            int carId,
            Timestamp dateTime) {

        return addReservationSqlService.createReservation(userId, serviceId, carId, dateTime);
    }

    public ContentDto<ReservationDto> getUserReservations(String userId) {
        List<ReservationDto> reservationDtoList = reservationSqlService.getReservations(userId)
                .stream().map(ReservationService::reservationDtoMapper).toList();

        return new ContentDto<>(reservationDtoList);
    }

    private static ReservationDto reservationDtoMapper(ReservationSqlRow reservationSqlRow) {
        return new ReservationDto(
                reservationSqlRow.id(),
                reservationSqlRow.servicesName(),
                reservationSqlRow.servicesPrice(),
                reservationSqlRow.carsMake(),
                reservationSqlRow.carsModel(),
                reservationSqlRow.carsYear(),
                reservationSqlRow.carsColour(),
                reservationSqlRow.resStartAt());
    }

    public static List<LocalTime> generateDailyTimeSlots() {
        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(19, 0);
        List<LocalTime> timeSlots = new ArrayList<>();

        while(startTime.isBefore(endTime)) {
            timeSlots.add(startTime);
            startTime = startTime.plusHours(1);
        }

        return timeSlots;
    }

    public static Map<LocalDate, List<LocalTime>> generateTimeSlotsForRange(LocalDate startDate, int days) {
        Map<LocalDate, List<LocalTime>> timeSlots = new HashMap<>();
        for (int i = 0; i < days; i++) {
            LocalDate date = startDate.plusDays(i);
            timeSlots.put(date, generateDailyTimeSlots());
        }
        return timeSlots;
    }

    public Map<LocalDate, List<LocalTime>> filterSlots(
            Map<LocalDate, List<LocalTime>> slots,
            List<ReservationStartEndTimes> reservations,
            int serviceLength) {
        Map<LocalDate, List<LocalTime>> availableSlots = new HashMap<>();

        for (Map.Entry<LocalDate, List<LocalTime>> entry : slots.entrySet()) {
            LocalDate date = entry.getKey();
            List<LocalTime> dailySlots = new ArrayList<>(entry.getValue());

            for (ReservationStartEndTimes reservation : reservations) {

                if (reservation.startAt().toLocalDate().equals(date)
                        || reservation.endAt().toLocalDate().equals(date)) {
                    dailySlots.removeIf(
                            slot -> !date.atTime(slot).plusHours(serviceLength).isBefore(reservation.startAt()) &&
                                    !date.atTime(slot).isAfter(reservation.endAt())
                    );
                }
            }

            availableSlots.put(date, dailySlots);
        }

        return availableSlots;
    }

    public Map<LocalDate, List<LocalTime>> getAvailableSlots() {
        Map<LocalDate, List<LocalTime>> slots = generateTimeSlotsForRange(
                LocalDate.parse("2023-11-13"),
                2);
        List<ReservationStartEndTimes> reservations = reservationSqlService.getReservationStartEndTimes()
                        .stream().map(this::toReservationStartEndTimes).toList();
        int serviceLength = 2;

        return filterSlots(slots, reservations, serviceLength);
    }

    private ReservationStartEndTimes toReservationStartEndTimes(
            ReservationStartEndTimesSqlRow reservationStartEndTimesSqlRow) {
        return new ReservationStartEndTimes(reservationStartEndTimesSqlRow.startAt(),
                reservationStartEndTimesSqlRow.endAt());
    }
}
