package ru.moskalenko.hr_platform.auth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Ответ при обновлении токена")
public record RefreshResponse(

        @Schema(description = "Новая пара токенов")
        TokenPair tokens
) {}
