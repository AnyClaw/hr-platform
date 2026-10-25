package ru.moskalenko.hr_platform.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.moskalenko.hr_platform.user.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
