package ru.voidlol.ce.parser;

import ru.voidlol.ce.command.domain.Command;

public interface ParserService {

    Command parse(String userInput);
}
