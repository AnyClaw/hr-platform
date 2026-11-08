package ru.moskalenko.hr_platform.user.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.moskalenko.hr_platform.shared.exception.UserNotFoundException;
import ru.moskalenko.hr_platform.user.dto.UserProfileResponse;
import ru.moskalenko.hr_platform.user.entity.User;
import ru.moskalenko.hr_platform.user.mapper.UserMapper;
import ru.moskalenko.hr_platform.user.repository.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public UserProfileResponse getCurrentUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        return userMapper.toProfileResponse(currentUser);
    }
}
