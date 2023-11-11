package com.example.dgbackend.service;

import com.example.dgbackend.common.dto.ContentDto;
import com.example.dgbackend.database.car.CarDto;
import com.example.dgbackend.database.car.sql.CarSqlRow;
import com.example.dgbackend.database.reservations.dto.ReservationDto;
import com.example.dgbackend.database.reservations.sql.AddReservationSqlService;
import com.example.dgbackend.database.reservations.sql.ReservationSqlRow;
import com.example.dgbackend.database.reservations.sql.ReservationSqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

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
}
