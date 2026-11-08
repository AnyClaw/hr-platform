package ru.moskalenko.hr_platform.user.mapper;

import org.springframework.stereotype.Component;
import ru.moskalenko.hr_platform.user.dto.StudentProfileMeResponse;
import ru.moskalenko.hr_platform.user.dto.StudentProfilePublicResponse;
import ru.moskalenko.hr_platform.user.dto.UserMeResponse;
import ru.moskalenko.hr_platform.user.dto.UserPublicResponse;
import ru.moskalenko.hr_platform.user.entity.StudentProfile;
import ru.moskalenko.hr_platform.user.entity.User;

@Component
public class UserMapper {

    public UserPublicResponse toPublicResponse(User user, StudentProfile profile) {
        StudentProfilePublicResponse profileDto = profile == null ? null :
                new StudentProfilePublicResponse(
                        profile.getInstitute(), profile.getGroupName(), profile.getCourse());

        return new UserPublicResponse(
                user.getId(), user.getFirstName(), user.getLastName(),
                user.getMiddleName(), user.getRole(), profileDto
        );
    }

    public UserMeResponse toMeResponse(User user, StudentProfile profile) {
        StudentProfileMeResponse profileDto = profile == null ? null :
                new StudentProfileMeResponse(
                        profile.getId(), profile.getInstitute(), profile.getGroupName(), profile.getCourse());

        return new UserMeResponse(
                user.getId(), user.getFirstName(), user.getLastName(), user.getMiddleName(),
                user.getBirthdate(), user.getPhone(), user.getEmail(), user.getRole(), profileDto
        );
    }
}