package com.example.dgbackend.database.car.sql;

public record CarSqlRow(
        int id,
        String make,
        String model,
        String productionYear) {
    public static final String ID = "id";
    public static final String MAKE = "make";
    public static final String MODEL = "model";
    public static final String PRODUCTION_YEAR = "production_year";
}
