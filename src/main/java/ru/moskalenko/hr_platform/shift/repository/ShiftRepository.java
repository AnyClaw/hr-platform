package ru.moskalenko.hr_platform.shift.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.moskalenko.hr_platform.shared.enums.ShiftStatus;
import ru.moskalenko.hr_platform.shift.dto.ShiftResponseDto;
import ru.moskalenko.hr_platform.shift.entity.Shift;
import ru.moskalenko.hr_platform.user.entity.User;

import java.time.Instant;

public interface ShiftRepository extends JpaRepository<Shift, Integer> {

    @Query("""
        SELECT new ru.moskalenko.hr_platform.shift.dto.ShiftResponseDto(
            s.id, s.title, s.description, s.startDatetime, s.endDatetime, s.status,
            s.requiredPeople,
            (SELECT CAST(COUNT(b) AS integer) FROM ShiftBooking b WHERE b.shift = s AND b.status != 'CANCELLED'),
            s.createdAt
        )
        FROM Shift s
        WHERE s.createdBy = :manager
        AND (:status IS NULL OR s.status = :status)
        AND (CAST(:fromDate AS timestamp) IS NULL OR s.endDatetime >= :fromDate)
        AND (CAST(:toDate AS timestamp) IS NULL OR s.startDatetime <= :toDate)
        AND (:upcoming IS NULL OR :upcoming = false OR s.startDatetime > CURRENT_TIMESTAMP)
    """)
    Page<ShiftResponseDto> findMyShiftsAsDto(
            @Param("manager") User manager,
            @Param("status") ShiftStatus status,
            @Param("fromDate") Instant fromDate,
            @Param("toDate") Instant toDate,
            @Param("upcoming") Boolean upcoming,
            Pageable pageable
    );
}