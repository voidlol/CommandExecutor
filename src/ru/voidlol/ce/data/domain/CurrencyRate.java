package ru.voidlol.ce.data.domain;

import ru.voidlol.ce.enums.CurrencyType;

import java.time.LocalDate;

public class CurrencyRate {

    private final CurrencyType currencyType;
    private final LocalDate date;
    private final Double value;

    public CurrencyRate(CurrencyType currencyType, LocalDate date, Double value) {
        this.currencyType = currencyType;
        this.date = date;
        this.value = value;
    }
}
