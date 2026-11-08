package ru.moskalenko.hr_platform.user.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "student_profiles")
@Data
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String institute;
    private String groupName;
    private Integer course;
}
