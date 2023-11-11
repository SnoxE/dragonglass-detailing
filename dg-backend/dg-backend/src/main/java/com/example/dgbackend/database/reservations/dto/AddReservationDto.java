package com.example.dgbackend.database.reservations.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public record AddReservationDto(
        @JsonProperty("id") int id,
        @JsonProperty("service_id") int serviceId,
        @JsonProperty("car_id") int carId,
        @JsonProperty("start_at") String startAt) {}
