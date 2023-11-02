package ru.job4j.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import ru.job4j.model.User;
import ru.job4j.util.JwtServiceUtil;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtServiceUtil jwtServiceUtil;

    public ResponseEntity<?> authentication(User user) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getLogin(), user.getPassword()
                ));
        return ResponseEntity
                .ok(jwtServiceUtil
                        .generateToken(userService
                                .loadUserByUsername(user.getLogin())));
    }
}
