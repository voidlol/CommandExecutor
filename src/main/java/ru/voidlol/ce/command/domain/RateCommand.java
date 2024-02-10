package ru.voidlol.ce.command.domain;

import lombok.Getter;
import ru.voidlol.ce.enums.AlgorithmType;
import ru.voidlol.ce.enums.CommandType;
import ru.voidlol.ce.enums.CurrencyType;

import java.time.LocalDate;

@Getter
public class RateCommand extends Command {

    private final AlgorithmType algorithmType;
    private final CurrencyType currencyType;
    private final LocalDate targetDate;

    public RateCommand(AlgorithmType algorithmType, CurrencyType currencyType, LocalDate targetDate) {
        super(CommandType.RATE);
        this.algorithmType = algorithmType;
        this.currencyType = currencyType;
        this.targetDate = targetDate;
    }

}
