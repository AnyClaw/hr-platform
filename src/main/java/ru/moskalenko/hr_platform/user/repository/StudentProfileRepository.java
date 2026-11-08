package ru.moskalenko.hr_platform.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.moskalenko.hr_platform.user.entity.StudentProfile;

import java.util.Optional;

public interface StudentProfileRepository extends JpaRepository<StudentProfile, Integer> {
    Optional<StudentProfile> findByUserId(Integer userId);
}