package com.example.dgbackend.service;

import com.example.dgbackend.database.user.dto.UserDto;
import com.example.dgbackend.database.user.sql.UserSqlService;
import com.example.dgbackend.common.exceptions.DgAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@Transactional
public class UserService {

    @Autowired
    private final UserSqlService userSqlService;

    public UserService(UserSqlService userSqlService) {
        this.userSqlService = userSqlService;
    }

    public UserDto validateUser(String email, String password) throws DgAuthException {
        return null;
    }

    public UserDto registerUser(
            String firstName,
            String lastName,
            String email,
            String password) throws DgAuthException {

        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if (email != null) email = email.toLowerCase();
        if(!pattern.matcher(email).matches()) throw new DgAuthException("Niepoprawny format adresu email");

        Integer count = userSqlService.getCountByEmail(email);
        if (count > 0) throw new DgAuthException("Adres email jest już w użyciu");

        userSqlService.createUser(firstName, lastName, email, password);

        return userSqlService.getUserByEmail(email);
    }
}
