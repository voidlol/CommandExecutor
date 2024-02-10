package ru.voidlol.ce.enums;

public enum Error {
    NO_DATE("No target date specified"),
    INVALID_CURRENCY("Invalid currency"),
    INVALID_DATE("Invalid date"),
    INVALID_COMMAND("Invalid command"),
    INVALID_ALGORITHM("Invalid algorithm"),
    INTERNAL_ERROR("Internal error");


    private final String error;

    Error(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
