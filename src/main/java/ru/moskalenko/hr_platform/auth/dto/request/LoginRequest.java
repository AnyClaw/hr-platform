package ru.moskalenko.hr_platform.auth.dto.request;

public record LoginRequest(
        String email,
        String password
) {
}
