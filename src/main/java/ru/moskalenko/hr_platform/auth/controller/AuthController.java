package ru.moskalenko.hr_platform.auth.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.moskalenko.hr_platform.auth.dto.request.LoginRequest;
import ru.moskalenko.hr_platform.auth.dto.response.LoginResponse;
import ru.moskalenko.hr_platform.auth.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;
    private final PasswordEncoder encoder;

    @PostMapping("/login")
    public LoginResponse authenticateUser(
            @RequestBody LoginRequest request
    ) {
        log.info("{}", request);
        return authService.authenticateUser(request);
    }

    @GetMapping("/password/generate")
    public String generate(@RequestBody String password) {
        return encoder.encode(password);
    }
}
