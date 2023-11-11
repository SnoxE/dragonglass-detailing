package com.example.dgbackend.database.reservations.sql;

import com.example.dgbackend.common.exceptions.DgAuthException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import static com.example.dgbackend.common.resource.ResourceManager.readSqlQuery;

@Service
public class AddReservationSqlService {

    public static final String INSERT_INTO_RESERVATIONS =
            readSqlQuery("sql/insert/insert_into_reservations.sql");

    JdbcOperations jdbcOperations;

    public AddReservationSqlService(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    private PreparedStatement preparedInsertIntoReservationsQuery(
            Connection connection,
            int userId,
            int serviceId,
            int carId,
            Timestamp dateTime) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT_INTO_RESERVATIONS);

        int parameterIndex = 0;

        statement.setInt(++parameterIndex, userId);
        statement.setInt(++parameterIndex, serviceId);
        statement.setInt(++parameterIndex, carId);
        statement.setTimestamp(++parameterIndex, dateTime);

        return statement;
    }

    public Integer createReservation(
            int userId,
            int serviceId,
            int carId,
            Timestamp dateTime) throws DgAuthException {
        try {
            return jdbcOperations.update(con -> preparedInsertIntoReservationsQuery(
                    con,
                    userId,
                    serviceId,
                    carId,
                    dateTime));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
