package ru.moskalenko.hr_platform.user.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.moskalenko.hr_platform.shared.exception.NotFoundException;
import ru.moskalenko.hr_platform.user.dto.UserMeResponse;
import ru.moskalenko.hr_platform.user.dto.UserPublicResponse;
import ru.moskalenko.hr_platform.user.entity.StudentProfile;
import ru.moskalenko.hr_platform.user.entity.User;
import ru.moskalenko.hr_platform.user.mapper.UserMapper;
import ru.moskalenko.hr_platform.user.repository.StudentProfileRepository;
import ru.moskalenko.hr_platform.user.repository.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final StudentProfileRepository studentProfileRepository;
    private final UserMapper userMapper;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public UserPublicResponse getPublicInfo(Integer id) {
        User user = getById(id);
        StudentProfile profile = studentProfileRepository.findByUserId(id).orElse(null);
        return userMapper.toPublicResponse(user, profile);
    }

    @Transactional(readOnly = true)
    public UserMeResponse getMe(Integer currentUserId) {
        User user = getById(currentUserId);
        StudentProfile profile = studentProfileRepository.findByUserId(currentUserId).orElse(null);
        return userMapper.toMeResponse(user, profile);
    }

    private User getById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Пользователь с id " + id + " не найден"));
    }
}
