package ru.voidlol.ce.enums;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Supplier;

public enum TargetDate {
    TOMORROW(() -> LocalDate.now().plusDays(1)),
    WEEK(() -> LocalDate.now().plusWeeks(1)),
    MONTH(() -> LocalDate.now().plusMonths(1));

    private final Supplier<LocalDate> targetDate;

    TargetDate(Supplier<LocalDate> targetDate) {
        this.targetDate = targetDate;
    }

    public LocalDate getTargetDate() {
        return targetDate.get();
    }

    public static Optional<TargetDate> findByName(String name) {
        return Arrays.stream(values())
                .filter(v -> v.name().equalsIgnoreCase(name))
                .findFirst();
    }
}
