package ru.voidlol.ce.data;

import ru.voidlol.ce.data.domain.CurrencyRate;
import ru.voidlol.ce.enums.CurrencyType;

import java.util.List;

public interface CurrencyParser {
    List<CurrencyRate> read(CurrencyType currencyType);
}
