package ru.moskalenko.hr_platform.shift.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.moskalenko.hr_platform.shared.enums.ShiftStatus;
import ru.moskalenko.hr_platform.shift.dto.ShiftResponseDto;
import ru.moskalenko.hr_platform.shift.repository.ShiftRepository;
import ru.moskalenko.hr_platform.user.entity.User;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ShiftService {

    private final ShiftRepository shiftRepository;

    @Transactional(readOnly = true)
    public Page<ShiftResponseDto> getMyShifts(ShiftStatus status, Instant from, Instant to, Boolean upcoming, int page, int size) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Pageable pageable;

        if (Boolean.TRUE.equals(upcoming)) {
            pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "startDatetime"));
        } else {
            pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        }

        return shiftRepository.findMyShiftsAsDto(currentUser, status, from, to, upcoming, pageable);
    }
}
