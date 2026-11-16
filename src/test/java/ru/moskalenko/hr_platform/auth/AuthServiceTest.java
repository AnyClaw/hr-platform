package ru.moskalenko.hr_platform.auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.moskalenko.hr_platform.auth.dto.request.LoginRequest;
import ru.moskalenko.hr_platform.auth.dto.response.LoginResponse;
import ru.moskalenko.hr_platform.auth.service.AuthService;
import ru.moskalenko.hr_platform.shared.exception.InvalidCredentialsException;
import ru.moskalenko.hr_platform.user.entity.User;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class AuthServiceTest {

    @Mock
    AuthenticationManager authenticationManager;

    @InjectMocks
    AuthService authService;

    @BeforeEach
    void beforeEach() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void authenticateUser_CorrectResponse() {
        LoginRequest loginRequest = new LoginRequest("example_email@email.com", "password");
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        User user = Mockito.mock(User.class);

        when(authenticationManager.authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(user);

        LoginResponse loginResponse = authService.authenticateUser(loginRequest);

        assertThat(loginResponse).isNotNull();
    }

    @Test
    //fixme Я не понимаю, как проверить, что при отсуствии или соотв статусе пользователя во внутреннюю бд, будет выбрасыватсья исключение.
    void authenticateUser_WrongPasswordThrowsInvalidCredentialsException() {
        LoginRequest loginRequest = new LoginRequest("right_email@email.com", "wrongPassword");

        when(authenticationManager.authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class))).thenThrow(BadCredentialsException.class);

        assertThrows(InvalidCredentialsException.class, () -> {
            authService.authenticateUser(loginRequest);
        });
    }
}
