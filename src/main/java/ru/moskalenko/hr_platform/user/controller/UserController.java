package ru.moskalenko.hr_platform.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.moskalenko.hr_platform.user.dto.UserProfileResponse;
import ru.moskalenko.hr_platform.user.service.UserService;
import ru.moskalenko.hr_platform.web.annotation.ApiUnauthorizedResponse;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "Users")
public class UserController {

    private final UserService userService;

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
}
