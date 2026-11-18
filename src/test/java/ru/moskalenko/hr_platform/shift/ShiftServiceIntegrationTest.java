package ru.moskalenko.hr_platform.shift;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.TestPropertySource;
import ru.moskalenko.hr_platform.shared.enums.ShiftStatus;
import ru.moskalenko.hr_platform.shift.dto.ShiftResponseDto;
import ru.moskalenko.hr_platform.shift.service.ShiftService;
import ru.moskalenko.hr_platform.user.entity.User;
import ru.moskalenko.hr_platform.user.repository.UserRepository;

import java.time.Instant;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(ShiftService.class)
@TestPropertySource(locations = "classpath:test.properties")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ExtendWith(MockitoExtension.class)
public class ShiftServiceIntegrationTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ShiftService shiftService;

    @Test
    @DisplayName("Тест проверяет, правильно ли возвращаются и обрабатываются смены менеджера, который их создал?")
    void shiftService_getMyShifts_SortingIsCorrectWithUpcomingEqualsTrue() {
        User user = userRepository.findById(3).get();
        boolean upcoming = true;
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(auth);

        Page<ShiftResponseDto> response = shiftService.getMyShifts(
                ShiftStatus.AVAILABLE,
                Mockito.mock(Instant.class),
                Mockito.mock(Instant.class),
                upcoming,
                1,
                10
        );

        assertTrue(response.getPageable().getSort().isSorted());
        assertNotNull(response.getPageable().getSort().getOrderFor("startDatetime"));
        assertThat(response.getPageable().getSort().getOrderFor("startDatetime").getDirection()).isEqualTo(Sort.Direction.ASC);
    }

    @Test
    @DisplayName("Тест проверяет, правильно ли возвращаются и обрабатываются смены менеджера, который их создал?")
    @Disabled
    void shiftService_getMyShifts_SortingIsCorrectWithUpcomingEqualsFalse() {
        User user = userRepository.findById(3).get();
        boolean upcoming = false;
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(auth);

        Page<ShiftResponseDto> response = shiftService.getMyShifts(
                ShiftStatus.AVAILABLE,
                Mockito.mock(Instant.class),
                Mockito.mock(Instant.class),
                upcoming,
                1,
                10
        );

        assertTrue(response.getPageable().getSort().isSorted());
        assertNotNull(response.getPageable().getSort().getOrderFor("createdAt"));
        assertThat(response.getPageable().getSort().getOrderFor("createdAt").getDirection()).isEqualTo(Sort.Direction.DESC);
    }


}
