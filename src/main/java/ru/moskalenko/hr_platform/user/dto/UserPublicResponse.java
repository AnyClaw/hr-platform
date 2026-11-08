package ru.moskalenko.hr_platform.user.dto;

import ru.moskalenko.hr_platform.shared.enums.UserRole;

public record UserPublicResponse(
        Integer id,
        String firstName,
        String lastName,
        String middleName,
        UserRole role,
        StudentProfilePublicResponse studentProfile
) {}