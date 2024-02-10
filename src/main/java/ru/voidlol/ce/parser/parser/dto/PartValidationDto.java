package ru.voidlol.ce.parser.parser.dto;

public record PartValidationDto<T>(T validated, String error) {

}
