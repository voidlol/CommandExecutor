package ru.voidlol.ce.enums;

public enum Message {

    ASK_FOR_COMMAND("Enter command:"),
    ASK_FOR_CONTINUE("Do you want to continue? [Y\\N]");

    private final String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
