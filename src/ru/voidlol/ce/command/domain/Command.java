package ru.voidlol.ce.command.domain;

import ru.voidlol.ce.enums.CommandType;

public abstract class Command {

    private CommandType commandType;

    public Command(CommandType commandType) {
        this.commandType = commandType;
    }

    public CommandType getCommandType() {
        return commandType;
    }
}
