package com.example.dgbackend.database.user.sql;

import com.example.dgbackend.database.user.dto.UserDto;
import com.example.dgbackend.common.exceptions.DgAuthException;
import com.example.dgbackend.database.user.sql.model.UserSqlRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Connection;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.example.dgbackend.common.resource.ResourceManager.readSqlQuery;

@Service
public class UserSqlService {

    private static final Logger log = LoggerFactory.getLogger(UserSqlService.class);

    private static final String INSERT_INTO_USERS = readSqlQuery("sql/insert/insert_into_users.sql");
//    private static final String INSERT_INTO_USERS = "INSERT INTO USERS(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD) " +
//        "VALUES (?, ?, ?, ?);";
    private static final String SELECT_COUNT_BY_EMAIL = readSqlQuery("sql/select/select_user_count_by_email.sql");
    private static final String SELECT_USER_BY_ID = readSqlQuery("sql/select/select_user_by_id.sql");
    private static final String SELECT_USER_BY_EMAIL = readSqlQuery("sql/select/select_user_by_email.sql");
    private static final String SELECT_USER_BY_EMAIL_AND_PASSWORD =
            readSqlQuery("sql/select/select_user_by_email_and_password.sql");

    private final JdbcOperations jdbcOperations;
    private final PasswordEncoder passwordEncoder;

    public UserSqlService(JdbcOperations jdbcOperations, PasswordEncoder passwordEncoder) {
        this.jdbcOperations = jdbcOperations;
        this.passwordEncoder = passwordEncoder;
    }

    private PreparedStatement preparedInsertIntoUsersQuery(
            Connection connection,
            String firstName,
            String lastName,
            String email,
            String password) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT_INTO_USERS);

        int parameterIndex = 0;

        statement.setString(++parameterIndex, firstName);
        if (lastName != null)
            statement.setString(++parameterIndex, lastName);
        else
            statement.setNull(++parameterIndex, JDBCType.VARCHAR.getVendorTypeNumber());
        statement.setString(++parameterIndex, email);
        statement.setString(++parameterIndex, passwordEncoder.encode(password));

        return statement;
    }

    public Integer createUser(String firstName, String lastName, String email, String password) throws DgAuthException {
        try {
//            KeyHolder keyHolder = new GeneratedKeyHolder();
           return jdbcOperations.update(con -> preparedInsertIntoUsersQuery(con, firstName, lastName, email,
                   password));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public UserDto getUserByEmailAndPassword(String email, String password) throws DgAuthException {
        return jdbcOperations.queryForObject(
                SELECT_USER_BY_EMAIL_AND_PASSWORD,
                userRowMapper,
                UserDto.class,
                email,
                password);
    }

    public Integer getCountByEmail(String email) {
        return jdbcOperations.queryForObject(SELECT_COUNT_BY_EMAIL, Integer.class, email);
    }
    public UserDto getUserById(int id) {
        return jdbcOperations.queryForObject(SELECT_USER_BY_ID, userRowMapper, id);
    }

    public UserDto getUserByEmail(String email) {
        return jdbcOperations.queryForObject(SELECT_USER_BY_EMAIL, userRowMapper, email);
    }

    private final RowMapper<UserDto> userRowMapper = ((resultSet, rowNum) -> {
        return new UserDto(
                resultSet.getInt(UserSqlRow.ID),
                resultSet.getString(UserSqlRow.FIRST_NAME),
                resultSet.getString(UserSqlRow.LAST_NAME),
                resultSet.getString(UserSqlRow.EMAIL),
                resultSet.getString(UserSqlRow.PASSWORD));
    });
}
