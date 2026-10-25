package ru.moskalenko.hr_platform.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.moskalenko.hr_platform.auth.dto.request.LoginRequest;
import ru.moskalenko.hr_platform.auth.dto.response.LoginResponse;
import ru.moskalenko.hr_platform.auth.JwtProperties;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtProperties jwtProperties;
    private final JwtUtils jwtUtils;

    public LoginResponse authenticateUser(LoginRequest loginRequest) {
        log.debug("Попытка аутентификации пользователя: {}", loginRequest.email());

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginRequest.email(), loginRequest.password()
        );

        // AuthenticationManager вызовет:
        //   - CustomUserDetailsService.loadUserByUsername()
        //   - PasswordEncoder.matches() для проверки пароля
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Long expiresInSeconds = jwtProperties.getExpiration() / 1000;

        String accessToken = jwtUtils.generateAccessTokenFromAuthentication(authentication, expiresInSeconds);
        String refreshToken = jwtUtils.generateRefreshToken(authentication.getName());

        log.info("Пользователь {} успешно аутентифицирован. Access токен истечёт через {} секунд",
                loginRequest.email(), expiresInSeconds);

        return new LoginResponse(accessToken, refreshToken, expiresInSeconds);
    }
}
