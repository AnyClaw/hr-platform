package ru.moskalenko.hr_platform.shared.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.moskalenko.hr_platform.shared.enums.UserRole;

public record UserSummary(
        @Schema(example = "1")
        Integer id,

        @Schema(example = "STUDENT")
        UserRole role,

        @Schema(example = "Иван")
        String firstName,

        @Schema(example = "Петров")
        String lastName,

        @Schema(example = "Сергеевич")
        String middleName,

        @Schema(example = "ivan.petrov@example.com")
        String email
){}
