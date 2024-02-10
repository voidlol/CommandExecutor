package ru.voidlol.ce.command.domain;

import ru.voidlol.ce.enums.CommandType;

public class ExitCommand extends Command {
    public ExitCommand() {
        super(CommandType.EXIT);
    }
}
