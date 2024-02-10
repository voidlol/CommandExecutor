package ru.voidlol.ce.command.dto;

import lombok.RequiredArgsConstructor;
import ru.voidlol.ce.enums.CurrencyType;

import java.time.LocalDate;

@RequiredArgsConstructor
public class Forecast {

    private final Double value;
    private final LocalDate targetDate;
    private final CurrencyType currencyType;

    @Override
    public String toString() {
        return String.format("Currency forecast for %s on %s is %.2f", currencyType, targetDate, value);
    }
}
