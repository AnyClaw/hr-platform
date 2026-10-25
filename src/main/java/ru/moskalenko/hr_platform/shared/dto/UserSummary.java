package ru.moskalenko.hr_platform.shared.dto;

import ru.moskalenko.hr_platform.shared.enums.UserRole;

public record UserSummary(
        Integer id,
        UserRole role,
        String firstName,
        String lastName,
        String middleName,
        String email
){}
