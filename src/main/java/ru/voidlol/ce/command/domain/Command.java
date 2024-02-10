package ru.voidlol.ce.command.domain;

import lombok.Getter;
import ru.voidlol.ce.enums.CommandType;

@Getter
public abstract class Command {

    private final CommandType commandType;

    public Command(CommandType commandType) {
        this.commandType = commandType;
    }

}
