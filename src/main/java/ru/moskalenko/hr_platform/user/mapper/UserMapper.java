package ru.moskalenko.hr_platform.user.mapper;

import org.mapstruct.Mapper;
import ru.moskalenko.hr_platform.user.dto.UserProfileResponse;
import ru.moskalenko.hr_platform.user.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserProfileResponse toProfileResponse(User user);
}