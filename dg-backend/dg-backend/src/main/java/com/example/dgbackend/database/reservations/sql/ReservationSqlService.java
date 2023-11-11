package com.example.dgbackend.database.reservations.sql;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.example.dgbackend.common.resource.ResourceManager.readSqlQuery;

@Service
public class ReservationSqlService {

    private static final Logger log = LoggerFactory.getLogger(ReservationSqlService.class);
    public static final String SELECT_RESERVATIONS_BY_USER_ID =
            readSqlQuery("sql/select/reservations/select_reservations_by_user_id.sql");

    JdbcOperations jdbcOperations;

    public ReservationSqlService(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    public List<ReservationSqlRow> getReservations(String userId) {
        return jdbcOperations.query(
                con -> preparedSelectReservationByUserIdQuery(con, userId),
                (rs, rowNum) -> {
                    try {
                        return extractReservationRow(rs);
                    } catch (JsonProcessingException e) {
                        log.error(
                                "Unable to retrieve cars due to unexpected exception (userId={}, message={})",
                                userId,
                                e.getMessage(),
                                e);
                        throw new RuntimeException(e);
                    }
                });
    }

    private PreparedStatement preparedSelectReservationByUserIdQuery(
            Connection connection,
            String userId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_RESERVATIONS_BY_USER_ID);

        int parameterIndex = 0;
        statement.setInt(++parameterIndex, Integer.parseInt(userId));

        return statement;
    }

    private ReservationSqlRow extractReservationRow(ResultSet resultSet) throws SQLException, JsonProcessingException {
        return new ReservationSqlRow(
                resultSet.getInt(ReservationSqlRow.ID),
                resultSet.getString(ReservationSqlRow.SERVICES_NAME),
                resultSet.getInt(ReservationSqlRow.SERVICES_PRICE),
                resultSet.getString(ReservationSqlRow.CARS_MAKE),
                resultSet.getString(ReservationSqlRow.CARS_MODEL),
                resultSet.getString(ReservationSqlRow.CARS_YEAR),
                resultSet.getString(ReservationSqlRow.CARS_COLOUR),
                resultSet.getTimestamp(ReservationSqlRow.RES_START_AT));
    }
}
