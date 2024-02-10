package ru.voidlol.ce.enums;

import lombok.Getter;

@Getter
public enum Message {

    ASK_FOR_COMMAND("Enter command:"),
    ASK_FOR_CONTINUE("Do you want to continue? [Y\\N]");

    private final String message;

    Message(String message) {
        this.message = message;
    }

}
