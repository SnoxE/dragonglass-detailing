package dgbackend.controller;

import dgbackend.database.user.dto.UserDto;
import dgbackend.database.user.sql.UserSqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HomeController {

    @Autowired
    private final UserSqlService userSqlService;

    public HomeController(UserSqlService userSqlService) {
        this.userSqlService = userSqlService;
    }

    @GetMapping
    public String home(Principal principal){ return "Hi, " + principal.getName(); }

    @PreAuthorize("hasAuthority('SCOPE_read')")
    @GetMapping("/api/secure")
    public String secure(Principal principal) {
        UserDto user = userSqlService.getUserByEmail(principal.getName());
        return "Hi, secure " + user;
    }

}
