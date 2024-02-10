package ru.voidlol.ce.enums;

import java.util.Arrays;
import java.util.Optional;

public enum Algorithm {
    LINEAR, STRAIGHT;

    public static Optional<Algorithm> findByName(String name) {
        return Arrays.stream(values())
                .filter(v -> v.name().equalsIgnoreCase(name))
                .findFirst();
    }
}
