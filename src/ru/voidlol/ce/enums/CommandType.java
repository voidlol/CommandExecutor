package ru.voidlol.ce.enums;

import java.util.Arrays;
import java.util.Optional;

public enum CommandType {
    RATE, EXIT;

    public static Optional<CommandType> findByName(String commandType) {
        return Arrays.stream(values())
                .filter(v -> v.name().equalsIgnoreCase(commandType))
                .findFirst();
    }
}
