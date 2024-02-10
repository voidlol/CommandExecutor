package ru.voidlol.ce.command.domain;

import ru.voidlol.ce.enums.Algorithm;
import ru.voidlol.ce.enums.CommandType;
import ru.voidlol.ce.enums.CurrencyType;

import java.time.LocalDate;

public class RateCommand extends Command {

    private final Algorithm algorithm;
    private final CurrencyType currencyType;
    private final LocalDate targetDate;

    public RateCommand(Algorithm algorithm, CurrencyType currencyType, LocalDate targetDate) {
        super(CommandType.RATE);
        this.algorithm = algorithm;
        this.currencyType = currencyType;
        this.targetDate = targetDate;
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }
}
