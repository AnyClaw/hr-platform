package ru.moskalenko.hr_platform.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import ru.moskalenko.hr_platform.shared.enums.UserRole;
import java.time.LocalDate;

@Schema(description = "Полная информация о профиле пользователя")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserProfileResponse(

        @Schema(example = "1")
        Integer id,

        @Schema(example = "Иван")
        String firstName,

        @Schema(example = "Петров")
        String lastName,

        @Schema(example = "Сергеевич", nullable = true)
        String middleName,

        @Schema(example = "2000-01-01", nullable = true)
        LocalDate birthdate,

        @Schema(example = "+79001234567")
        String phone,

        @Schema(example = "ivan.petrov@example.com")
        String email,

        @Schema(example = "STUDENT")
        UserRole role,

        StudentProfileDto studentProfile
) {
}
