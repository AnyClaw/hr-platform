package ru.moskalenko.hr_platform.auth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.moskalenko.hr_platform.shared.dto.UserSummary;

@Schema(description = "Ответ при успешном входе в систему")
public record LoginResponse(

        @Schema(description = "Пара токенов")
        TokenPair tokens,

        @Schema(description = "Информация о пользователе")
        UserSummary user
) {
}