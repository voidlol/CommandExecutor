package ru.voidlol.ce.parser.parser;

import ru.voidlol.ce.command.domain.Command;
import ru.voidlol.ce.command.domain.RateCommand;
import ru.voidlol.ce.enums.Error;
import ru.voidlol.ce.enums.*;
import ru.voidlol.ce.parser.parser.dto.PartValidationDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class RateCommandParser implements CommandParser {

    private static final int CURRENCY_INDEX = 0;
    private static final String ALGORITHM_KEY = "-a";
    private static final String DATE_KEY = "-d";
    private static final int MINIMUM_ARGUMENTS = 4;

    @Override
    public Command parse(String[] command) {
        String[] arguments = new String[command.length - 1];
        System.arraycopy(command, 1, arguments, 0, arguments.length);
        List<String> arguments1 = List.of(arguments);
        PartValidationDto<CurrencyType> currencyType = parseCurrency(arguments1);
        PartValidationDto<Algorithm> algorithm = parseAlgorithm(arguments1);
        PartValidationDto<LocalDate> targetDate = parseTargetDate(arguments1);
        checkErrors(currencyType, algorithm, targetDate);
        return new RateCommand(algorithm.validated(), currencyType.validated(), targetDate.validated());
    }

    private void checkErrors(PartValidationDto<CurrencyType> currencyType,
                             PartValidationDto<Algorithm> algorithm,
                             PartValidationDto<LocalDate> targetDate) {
        List<String> errors = Stream.of(currencyType.error(), algorithm.error(), targetDate.error())
                .filter(Objects::nonNull)
                .toList();
        if (!errors.isEmpty()) throw new RuntimeException(String.join("\n", errors));
    }

    @Override
    public CommandType getType() {
        return CommandType.RATE;
    }

    private PartValidationDto<CurrencyType> parseCurrency(List<String> arguments) {
        return CurrencyType.findByName(arguments.get(CURRENCY_INDEX))
                .map(ct -> new PartValidationDto<>(ct, null))
                .orElseGet(() -> new PartValidationDto<>(null, Error.INVALID_CURRENCY.getError()));
    }

    private PartValidationDto<Algorithm> parseAlgorithm(List<String> arguments) {
        int indexOfAlgorithmKey = arguments.indexOf(ALGORITHM_KEY);
        if (indexOfAlgorithmKey == -1) {
            return new PartValidationDto<>(Algorithm.STRAIGHT, null);
        }
        if (indexOfAlgorithmKey == arguments.size()) {
            return new PartValidationDto<>(null, Error.INVALID_ALGORITHM.getError());
        }
        return Algorithm.findByName(arguments.get(indexOfAlgorithmKey + 1))
                .map(a -> new PartValidationDto<>(a, null))
                .orElseGet(() -> new PartValidationDto<>(null, Error.INVALID_ALGORITHM.getError()));
    }

    private PartValidationDto<LocalDate> parseTargetDate(List<String> arguments) {
        int indexOfTargetDateKey = arguments.indexOf(DATE_KEY);
        if (indexOfTargetDateKey == -1 || indexOfTargetDateKey == arguments.size()) {
            return new PartValidationDto<>(null, Error.NO_DATE.getError());
        }
        return TargetDate.findByName(arguments.get(indexOfTargetDateKey + 1))
                .map(TargetDate::getTargetDate)
                .map(ld -> new PartValidationDto<>(ld, null))
                .orElseGet(() -> parseCustomDate(arguments.get(indexOfTargetDateKey + 1)));
    }

    private PartValidationDto<LocalDate> parseCustomDate(String customDate) {
        try {
            LocalDate ld = LocalDate.parse(customDate, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            return new PartValidationDto<>(ld, null);
        } catch (DateTimeParseException e) {
            return new PartValidationDto<>(null, Error.INVALID_DATE.getError());
        }
    }
}
