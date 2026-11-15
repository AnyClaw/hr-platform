package ru.moskalenko.hr_platform.shift.entity;

import jakarta.persistence.*;
import lombok.Data;
import ru.moskalenko.hr_platform.shared.enums.BookingStatus;
import ru.moskalenko.hr_platform.user.entity.User;

import java.time.Instant;

@Entity
@Table(name = "shift_bookings")
@Data
public class ShiftBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shift_id")
    private Shift shift;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Instant bookedAt;
    private Instant startedAt;
    private Instant finishedAt;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    private String cancellationReason;
}