package ru.voidlol.ce.data.repository;

import ru.voidlol.ce.data.CurrencyParser;
import ru.voidlol.ce.data.domain.CurrencyRate;
import ru.voidlol.ce.enums.CurrencyType;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class InMemoryCurrencyRepository implements CurrencyRepository {

    private final Map<CurrencyType, List<CurrencyRate>> data;
    private final CurrencyParser currencyParser;

    public InMemoryCurrencyRepository(CurrencyParser currencyParser) {
        this.data = new EnumMap<>(CurrencyType.class);
        this.currencyParser = currencyParser;
    }

    @Override
    public List<CurrencyRate> findAllByType(CurrencyType currencyType) {
        if (!data.containsKey(currencyType)) {
            populateData(currencyType);
        }
        return data.get(currencyType);
    }

    private void populateData(CurrencyType currencyType) {
        data.put(currencyType, currencyParser.read(currencyType));
    }
}
