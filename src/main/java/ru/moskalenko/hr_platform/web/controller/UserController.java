package ru.moskalenko.hr_platform.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.moskalenko.hr_platform.shared.enums.ShiftStatus;
import ru.moskalenko.hr_platform.shift.dto.ShiftResponseDto;
import ru.moskalenko.hr_platform.shift.service.ShiftService;
import ru.moskalenko.hr_platform.user.dto.UserProfileResponse;
import ru.moskalenko.hr_platform.user.service.UserService;
import ru.moskalenko.hr_platform.web.annotation.ApiBadRequestResponse;
import ru.moskalenko.hr_platform.web.annotation.ApiForbiddenResponse;
import ru.moskalenko.hr_platform.web.annotation.ApiUnauthorizedResponse;

import java.time.Instant;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "Users")
public class UserController {

    private final UserService userService;
    private final ShiftService shiftService;

    @GetMapping("/me")
    @Operation(summary = "Получение профиля текущего пользователя")
    @ApiResponse(
            responseCode = "200", description = "Профиль успешно получен",
            content = @Content(schema = @Schema(implementation = UserProfileResponse.class))
    )
    @ApiUnauthorizedResponse
    public UserProfileResponse getCurrentUserProfile() {
        return userService.getCurrentUserProfile();
    }

    @GetMapping("/me/shifts")
    @PreAuthorize("hasAuthority('MANAGER')")
    @Operation(
            summary = "Получить смены, созданные текущим начальником",
            description = "Возвращает список смен с фильтрацией и пагинацией. Доступно только пользователям с ролью MANAGER."
    )
    @ApiBadRequestResponse
    @ApiUnauthorizedResponse
    @ApiForbiddenResponse
    public Page<ShiftResponseDto> getMyShifts(

            @Parameter(description = "Фильтр по статусу смены")
            @RequestParam(required = false)
            ShiftStatus status,

            @Parameter(description = "Начало периода (включительно). Формат ISO 8601", example = "2026-11-01T00:00:00Z")
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            Instant from,

            @Parameter(description = "Конец периода (включительно). Формат ISO 8601", example = "2026-11-30T23:59:59Z")
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            Instant to,

            @Parameter(example = "true")
            @RequestParam(required = false)
            Boolean upcoming,

            @Parameter(description = "Номер страницы (начиная с 0)", example = "0")
            @RequestParam(defaultValue = "0")
            int page,

            @Parameter(example = "20")
            @RequestParam(defaultValue = "20")
            int size
    ) {
        return shiftService.getMyShifts(status, from, to, upcoming, page, size);
    }
}
