package ru.voidlol.ce.enums;

import java.util.Arrays;
import java.util.Optional;

public enum AlgorithmType {
    LINEAR, STRAIGHT;

    public static Optional<AlgorithmType> findByName(String name) {
        return Arrays.stream(values())
                .filter(v -> v.name().equalsIgnoreCase(name))
                .findFirst();
    }
}
