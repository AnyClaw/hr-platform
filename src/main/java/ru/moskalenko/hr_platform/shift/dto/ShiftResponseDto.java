package ru.moskalenko.hr_platform.shift.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.moskalenko.hr_platform.shared.enums.ShiftStatus;

import java.time.Instant;

@Schema(description = "Краткая информация о смене для списка")
public record ShiftResponseDto(

        @Schema(example = "1")
        Integer id,

        @Schema(example = "Утренняя смена поддержки")
        String title,

        @Schema(example = "Обработка входящих заявок от пользователей", nullable = true)
        String description,

        @Schema(example = "2026-10-05T09:00:00Z")
        Instant startDatetime,

        @Schema(example = "2026-10-05T17:00:00Z")
        Instant endDatetime,

        @Schema(example = "AVAILABLE")
        ShiftStatus status,

        @Schema(example = "3")
        Integer requiredPeople,

        @Schema(example = "1")
        Integer bookedCount,

        @Schema(example = "2")
        Integer availableSlots,

        @Schema(example = "2026-09-01T12:00:00Z")
        Instant createdAt
) {
    public ShiftResponseDto(
            Integer id, String title, String description,
            Instant startDatetime, Instant endDatetime, ShiftStatus status,
            Integer requiredPeople, Integer bookedCount, Instant createdAt
    ) {
        this(id, title, description, startDatetime, endDatetime, status, requiredPeople,
                bookedCount, requiredPeople - bookedCount, createdAt);
    }
}