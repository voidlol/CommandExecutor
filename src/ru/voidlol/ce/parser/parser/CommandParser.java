package ru.voidlol.ce.parser.parser;

import ru.voidlol.ce.command.domain.Command;
import ru.voidlol.ce.enums.CommandType;

public interface CommandParser {

    Command parse(String[] command);
    CommandType getType();
}
