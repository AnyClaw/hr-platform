package ru.moskalenko.hr_platform.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.moskalenko.hr_platform.auth.dto.request.LoginRequest;
import ru.moskalenko.hr_platform.auth.dto.request.RefreshRequest;
import ru.moskalenko.hr_platform.auth.dto.response.LoginResponse;
import ru.moskalenko.hr_platform.auth.JwtProperties;
import ru.moskalenko.hr_platform.auth.dto.response.RefreshResponse;
import ru.moskalenko.hr_platform.auth.dto.response.TokenPair;
import ru.moskalenko.hr_platform.shared.dto.UserSummary;
import ru.moskalenko.hr_platform.shared.exception.InvalidCredentialsException;
import ru.moskalenko.hr_platform.shared.exception.InvalidTokenException;
import ru.moskalenko.hr_platform.user.entity.User;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtProperties jwtProperties;
    private final JwtUtils jwtUtils;
    private final AuthUserDetailsService userDetailsService;

    public LoginResponse authenticateUser(LoginRequest loginRequest) {
        log.debug("Попытка аутентификации пользователя: {}", loginRequest.email());

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginRequest.email(), loginRequest.password()
        );

        try {
            // AuthenticationManager вызовет:
            //   - CustomUserDetailsService.loadUserByUsername()
            //   - PasswordEncoder.matches() для проверки пароля
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            User user = (User) authentication.getPrincipal();
            UserSummary userSummary = new UserSummary(
                    user.getId(),
                    user.getRole(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getMiddleName(),
                    user.getEmail()
            );

            Long expiresInSeconds = jwtProperties.getExpiration() / 1000;

            String accessToken = jwtUtils.generateAccessTokenFromAuthentication(authentication, expiresInSeconds);
            String refreshToken = jwtUtils.generateRefreshToken(authentication.getName());

            log.info("Пользователь {} успешно аутентифицирован. Access токен истечёт через {} секунд",
                    loginRequest.email(), expiresInSeconds);

            TokenPair tokens = new TokenPair(accessToken, refreshToken, expiresInSeconds);
            return new LoginResponse(tokens, userSummary);
        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException("Неверный email или пароль");
        }
    }

    @Transactional(readOnly = true)
    public RefreshResponse refreshAccessToken(RefreshRequest request) {
        log.debug("Попытка обновления токена");

        String refreshToken = request.refreshToken();

        if (!jwtUtils.isTokenValid(refreshToken))
            throw new InvalidTokenException("Токен недействителен или истек");

        String username = jwtUtils.extractUsername(refreshToken);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String role = jwtUtils.serializeRole(userDetails);
        Long expiresInSecond = jwtProperties.getExpiration() / 1000;

        String newAccessToken = jwtUtils.generateAccessTokenFromCredentials(username, role, expiresInSecond);
        log.info("Access токен успешно обновлен для пользователя: {}", username);

        return new RefreshResponse(new TokenPair(newAccessToken, refreshToken, expiresInSecond));
    }
}
