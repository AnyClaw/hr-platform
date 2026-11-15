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
        responseCode = "403",
        description = "Доступ запрещен (недостаточно прав)",
        content = @Content(
                schema = @Schema(implementation = ErrorResponse.class),
                examples = @ExampleObject(
                        name = "Forbidden Error",
                        value = """
                {
                  "timestamp": "2026-10-25T20:16:57.793Z",
                  "status": 403,
                  "error": "Forbidden",
                  "message": "string",
                  "path": "string"
                }
            """
                )
        )
)
public @interface ApiForbiddenResponse {
}