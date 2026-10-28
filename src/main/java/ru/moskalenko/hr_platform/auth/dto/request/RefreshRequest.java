package ru.moskalenko.hr_platform.auth.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RefreshRequest(

        @NotBlank(message = "Refresh токен не может быть пустым")
        String refreshToken
) {
}
