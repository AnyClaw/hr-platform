package ru.moskalenko.hr_platform.shift.specification;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import ru.moskalenko.hr_platform.shift.entity.Shift;
import ru.moskalenko.hr_platform.user.entity.User;

import java.time.Instant;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ShiftSpecification {

    public static Specification<Shift> createdBy(User manager) {
        return (root, query, cb) -> cb.equal(root.get("createdBy"), manager);
    }

    public static Specification<Shift> hasStatus(String status) {
        return (root, query, cb) ->
                (status == null || status.isBlank())
                        ? cb.conjunction()
                        : cb.equal(root.get("status"), status);
    }

    public static Specification<Shift> endsAfter(Instant from) {
        return (root, query, cb) ->
                from == null
                        ? cb.conjunction()
                        : cb.greaterThanOrEqualTo(root.get("endDatetime"), from);
    }

    public static Specification<Shift> startsBefore(Instant to) {
        return (root, query, cb) ->
                to == null
                        ? cb.conjunction()
                        : cb.lessThanOrEqualTo(root.get("startDatetime"), to);
    }

    public static Specification<Shift> isUpcoming(Boolean upcoming) {
        return (root, query, cb) ->
                (upcoming == null || !upcoming)
                        ? cb.conjunction()
                        : cb.greaterThan(root.get("startDatetime"), Instant.now());
    }

    public static Specification<Shift> buildFilter(
            User manager, String status, Instant from, Instant to, Boolean upcoming
    ) {
        return Specification
                .where(createdBy(manager))
                .and(hasStatus(status))
                .and(endsAfter(from))
                .and(startsBefore(to))
                .and(isUpcoming(upcoming));
    }
}
