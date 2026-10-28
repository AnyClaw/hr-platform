package ru.moskalenko.hr_platform.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.moskalenko.hr_platform.auth.dto.request.LoginRequest;
import ru.moskalenko.hr_platform.auth.dto.request.RefreshRequest;
import ru.moskalenko.hr_platform.auth.dto.response.LoginResponse;
import ru.moskalenko.hr_platform.auth.dto.response.RefreshResponse;
import ru.moskalenko.hr_platform.auth.service.AuthService;
import ru.moskalenko.hr_platform.web.annotation.ApiBadRequestResponse;
import ru.moskalenko.hr_platform.web.annotation.ApiUnauthorizedResponse;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Authentication")
public class AuthController {

    private final AuthService authService;
    private final PasswordEncoder encoder;

    @PostMapping("/login")
    @Operation(summary = "Вход в систему")
    @ApiResponse(
            responseCode = "200", description = "Успешный вход",
            content = @Content(schema = @Schema(implementation = LoginResponse.class))
    )
    @ApiBadRequestResponse
    @ApiUnauthorizedResponse
    public LoginResponse authenticateUser(
            @Valid @RequestBody LoginRequest request
    ) {
        log.info("{}", request);
        return authService.authenticateUser(request);
    }

    @PostMapping("/refresh")
    @Operation(summary = "Обновление access токена")
    @ApiResponse(
        responseCode = "200", description = "Токены успешно обновлены",
        content = @Content(schema = @Schema(implementation = RefreshResponse.class))
    )
    @ApiBadRequestResponse
    @ApiUnauthorizedResponse
    public RefreshResponse refreshToken(
            @Valid @RequestBody RefreshRequest request
    ) {
        return authService.refreshAccessToken(request);
    }

    @GetMapping("/password/generate")
    @Deprecated
    public String generate(@RequestBody String password) {
        return encoder.encode(password);
    }
}
