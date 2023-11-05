package ru.job4j.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.job4j.model.User;
import ru.job4j.service.AuthService;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("short_url/login")
    public ResponseEntity<?> auth(@RequestBody User user) {
        return authService.authentication(user);
    }

}
