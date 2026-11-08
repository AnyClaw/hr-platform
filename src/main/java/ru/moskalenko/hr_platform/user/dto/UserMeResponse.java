package ru.moskalenko.hr_platform.user.dto;

import ru.moskalenko.hr_platform.shared.enums.UserRole;

import java.time.LocalDate;

public record UserMeResponse(
        Integer id,
        String firstName,
        String lastName,
        String middleName,
        LocalDate birthdate,
        String phone,
        String email,
        UserRole role,
        StudentProfileMeResponse studentProfile
) {}
