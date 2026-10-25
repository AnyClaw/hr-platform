package ru.moskalenko.hr_platform.auth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.moskalenko.hr_platform.shared.dto.UserSummary;

public record LoginResponse(
        @Schema(example = "eyJhbGciOiJIUzUxMiJ9...")
        String accessToken,

        @Schema(example = "eyJhbGciOiJIUzUxMiJ9...")
        String refreshToken,

        @Schema(example = "900")
        Long expiresIn,

        UserSummary user
) {
}
