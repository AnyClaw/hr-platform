package ru.moskalenko.hr_platform.auth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Пара JWT токенов")
public record TokenPair(

        @Schema(description = "Токен доступа", example = "eyJhbGciOiJIUzUxMiJ9...")
        String accessToken,

        @Schema(description = "Токен обновления", example = "eyJhbGciOiJIUzUxMiJ9...")
        String refreshToken,

        @Schema(description = "Время жизни access токена (в секундах)", example = "900")
        Long expiresIn
) {}
