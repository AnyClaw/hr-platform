package ru.moskalenko.hr_platform.auth.dto.response;

import ru.moskalenko.hr_platform.shared.dto.UserSummary;

public record LoginResponse(
        String accessToken,
        String refreshToken,
        Long expiresIn
//        UserSummary user
) {
}
