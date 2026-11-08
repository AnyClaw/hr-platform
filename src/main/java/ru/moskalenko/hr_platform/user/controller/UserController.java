package ru.moskalenko.hr_platform.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.moskalenko.hr_platform.user.dto.UserMeResponse;
import ru.moskalenko.hr_platform.user.dto.UserPublicResponse;
import ru.moskalenko.hr_platform.user.entity.User;
import ru.moskalenko.hr_platform.user.mapper.UserMapper;
import ru.moskalenko.hr_platform.user.service.UserService;
import ru.moskalenko.hr_platform.web.annotation.ApiBadRequestResponse;
import ru.moskalenko.hr_platform.web.annotation.ApiUnauthorizedResponse;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    @Operation(summary = "Публичная информация о пользователе")
    @ApiBadRequestResponse
    @ApiUnauthorizedResponse
    public UserPublicResponse getUser(@PathVariable Integer id) {
        return userService.getPublicInfo(id);
    }

    @GetMapping("/me")
    @Operation(summary = "Публичная информация о пользователе")
    @ApiBadRequestResponse
    @ApiUnauthorizedResponse
    public UserMeResponse getUserMe(@AuthenticationPrincipal User currentUser) {
        return userService.getMe(currentUser.getId());
    }
}