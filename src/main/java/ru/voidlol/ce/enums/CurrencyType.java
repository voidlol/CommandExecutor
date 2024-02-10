package ru.voidlol.ce.enums;

import java.util.Arrays;
import java.util.Optional;

public enum CurrencyType {
    USD, EUR, TRY, KZT;

    public static Optional<CurrencyType> findByName(String name) {
        return Arrays.stream(values())
                .filter(v -> v.name().equalsIgnoreCase(name))
                .findFirst();
    }
}
