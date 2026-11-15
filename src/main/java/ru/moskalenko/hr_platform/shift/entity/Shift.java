package ru.moskalenko.hr_platform.shift.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.moskalenko.hr_platform.shared.enums.ShiftStatus;
import ru.moskalenko.hr_platform.user.entity.User;

import java.time.Instant;

@Entity
@Table(name = "shifts")
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Shift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String description;
    private Instant startDatetime;
    private Instant endDatetime;
    private Integer requiredPeople;

    @Enumerated(EnumType.STRING)
    private ShiftStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = Instant.now();
        }
        if (status == null) {
            status = ShiftStatus.AVAILABLE;
        }
    }
}
