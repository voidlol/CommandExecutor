package ru.voidlol.ce.parser.parser;

import ru.voidlol.ce.command.domain.Command;
import ru.voidlol.ce.command.domain.ExitCommand;
import ru.voidlol.ce.enums.CommandType;
import ru.voidlol.ce.parser.parser.CommandParser;

public class ExitCommandParser implements CommandParser {
    @Override
    public Command parse(String[] command) {
        return new ExitCommand();
    }

    @Override
    public CommandType getType() {
        return CommandType.EXIT;
    }
}
