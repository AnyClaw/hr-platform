package ru.moskalenko.hr_platform.web.annotation;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import ru.moskalenko.hr_platform.shared.dto.ErrorResponse;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponse(
        responseCode = "401",
        description = "Неавторизованный доступ",
        content = @Content(
                schema = @Schema(implementation = ErrorResponse.class),
                examples = @ExampleObject(
                        name = "Unauthorized Error",
                        value = """
                {
                  "timestamp": "2026-10-25T20:16:57.793Z",
                  "status": 401,
                  "error": "Unauthorized",
                  "message": "Неверный email или пароль",
                  "path": "/api/auth/login"
                }
            """
                )
        )
)
public @interface ApiUnauthorizedResponse {
}
