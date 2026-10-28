package ru.moskalenko.hr_platform.shared.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Map;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Стандартный формат ошибки")
public record ErrorResponse(

        @Schema(example = "2026-10-25T20:16:57.793Z")
        LocalDateTime timestamp,

        @Schema(example = "400")
        int status,

        @Schema(example = "Bad Request")
        String error,

        @Schema(example = "Ошибка валидации данных")
        String message,

        Map<String, String> details,
        String path
) {}
