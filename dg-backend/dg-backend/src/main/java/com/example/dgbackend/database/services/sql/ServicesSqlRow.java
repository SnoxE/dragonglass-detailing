package com.example.dgbackend.database.services.sql;

import java.sql.Time;

public record ServicesSqlRow(
        int id,
        String name,
        int price,
        Time length,
        String car_size) {
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PRICE = "price";
    public static final String LENGTH = "length";
    public static final String CAR_SIZE = "car_size";
}
