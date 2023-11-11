package com.example.dgbackend.database.reservations.sql;

import java.sql.Timestamp;

public record ReservationSqlRow(
        int id,
        String servicesName,
        int servicesPrice,
        String carsMake,
        String carsModel,
        String carsYear,
        String carsColour,
        Timestamp resStartAt) {
    public static final String ID = "res_id";
    public static final String SERVICES_NAME = "services_name";
    public static final String SERVICES_PRICE = "services_price";
    public static final String CARS_MAKE = "cars_make";
    public static final String CARS_MODEL = "cars_model";
    public static final String CARS_YEAR = "cars_year";
    public static final String CARS_COLOUR = "cars_colour";
    public static final String RES_START_AT = "res_start_at";
}
