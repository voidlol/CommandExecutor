package ru.voidlol.ce.data.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.voidlol.ce.enums.CurrencyType;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CurrencyRate {

    private CurrencyType currencyType;
    private LocalDate date;
    private Double rate;
    private Integer nominal;

    public CurrencyRate(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    public CurrencyRate copyAtDate(LocalDate date) {
        return new CurrencyRate(currencyType, date, rate, nominal);
    }

}
