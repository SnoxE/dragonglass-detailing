package com.example.dgbackend.database.services.sql;

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
import java.util.Optional;

import static com.example.dgbackend.common.resource.ResourceManager.readSqlQuery;

@Service
public class ServicesSqlService {

    private static final Logger log = LoggerFactory.getLogger(ServicesSqlService.class);
    private static final String SELECT_SERVICE_BY_NAME =
            readSqlQuery("sql/select/services/select_service_by_name.sql");

    private static final String SELECT_SERVICE_ID_BY_NAME_AND_CAR_SIZE =
            readSqlQuery("sql/select/services/select_service_id_by_name_and_car_size.sql");

    private final JdbcOperations jdbcOperations;

    public ServicesSqlService(JdbcOperations jdbcOperations) { this.jdbcOperations = jdbcOperations; }

    private PreparedStatement preparedSelectServiceByNameStatement(
            Connection connection,
            String serviceName) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_SERVICE_BY_NAME);

        int parameterIndex = 0;
        statement.setString(++parameterIndex, serviceName);

        return statement;
    }

    public List<ServicesSqlRow> getServiceByName(String serviceName) {
        return jdbcOperations.query(
                con -> preparedSelectServiceByNameStatement(con, serviceName),
                (rs, rowNum) -> {
                    try {
                        return extractServiceRow(rs);
                    } catch (JsonProcessingException e) {
                        log.error(
                                "Unable to retrieve service due to unexpected exception (serviceName={}, message={})",
                                serviceName,
                                e.getMessage(),
                                e);
                        throw new RuntimeException(e);
                    }
                });
    }

    public Optional<Integer> getServiceIdByNameAndCarSize(String serviceName, String carSize) {
        return Optional.ofNullable(jdbcOperations.queryForObject(
                SELECT_SERVICE_ID_BY_NAME_AND_CAR_SIZE,
                Integer.class,
                serviceName,
                carSize));
    }

    private PreparedStatement preparedSelectServiceIdStatement(
            Connection connection,
            String serviceName,
            String carSize) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_SERVICE_ID_BY_NAME_AND_CAR_SIZE);

        int parameterIndex = 0;
        statement.setString(++parameterIndex, serviceName);
        statement.setString(++parameterIndex, carSize);

        return statement;
    }

    private ServicesSqlRow extractServiceRow(ResultSet resultSet) throws SQLException, JsonProcessingException {
        return new ServicesSqlRow(
                resultSet.getInt(ServicesSqlRow.ID),
                resultSet.getString(ServicesSqlRow.NAME),
                resultSet.getInt(ServicesSqlRow.PRICE),
                resultSet.getTime(ServicesSqlRow.LENGTH),
                resultSet.getString(ServicesSqlRow.CAR_SIZE));
    }
}
