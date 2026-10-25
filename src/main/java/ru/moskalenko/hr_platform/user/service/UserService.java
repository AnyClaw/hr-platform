package ru.moskalenko.hr_platform.user.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.moskalenko.hr_platform.user.entity.User;
import ru.moskalenko.hr_platform.user.repository.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
