package ru.voidlol.ce.data.repository;

import ru.voidlol.ce.data.domain.CurrencyRate;
import ru.voidlol.ce.enums.CurrencyType;

import java.util.List;

public interface CurrencyRepository {

    List<CurrencyRate> findAllByType(CurrencyType currencyType);

}
