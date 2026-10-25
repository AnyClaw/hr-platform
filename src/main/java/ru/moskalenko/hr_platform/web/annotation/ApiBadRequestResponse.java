package ru.moskalenko.hr_platform.web.annotation;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import ru.moskalenko.hr_platform.shared.dto.ErrorResponse;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponse(
        responseCode = "400",
        description = "Ошибка валидации входных данных",
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
)
public @interface ApiBadRequestResponse {
}
