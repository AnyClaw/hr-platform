package ru.moskalenko.hr_platform.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Университетская информация студента")
public record StudentProfileDto(
        @Schema(description = "ID профиля", example = "1")
        Integer id,

        @Schema(description = "Институт", example = "ИТТСУ")
        String institute,

        @Schema(description = "Название группы", example = "УБВТ2301")
        String groupName,

        @Schema(description = "Курс", example = "3")
        Integer course
) {
}
