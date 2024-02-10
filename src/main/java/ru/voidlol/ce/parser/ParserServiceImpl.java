package ru.voidlol.ce.parser;

import ru.voidlol.ce.command.domain.Command;
import ru.voidlol.ce.enums.CommandType;
import ru.voidlol.ce.enums.Error;
import ru.voidlol.ce.parser.parser.CommandParser;

import java.util.Map;

public class ParserServiceImpl implements ParserService {

    private final Map<CommandType, CommandParser> validatorMap;
    private static final int COMMAND_TYPE_INDEX = 0;

    public ParserServiceImpl(Map<CommandType, CommandParser> validatorMap) {
        this.validatorMap = validatorMap;
    }

    @Override
    public Command parse(String userInput) {
        String[] split = userInput.split("\\s+");
        CommandType commandType = parseCommandType(split[COMMAND_TYPE_INDEX]);
        return validatorMap.get(commandType).parse(split);
    }

    private CommandType parseCommandType(String commandType) {
        return CommandType.findByName(commandType)
                .orElseThrow(() -> new RuntimeException(Error.INVALID_COMMAND.getError()));
    }
}
