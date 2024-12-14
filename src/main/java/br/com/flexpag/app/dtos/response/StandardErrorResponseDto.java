package br.com.flexpag.app.dtos.response;

import java.time.Instant;
import java.util.Set;

public record StandardErrorResponseDto(
        Instant timestamp,
        Integer status,
        String error,
        String message,
        String path,
        Set<FieldErrorDto> errors) {
    @Override
    public Instant timestamp() {
        return timestamp;
    }

    @Override
    public Integer status() {
        return status;
    }

    @Override
    public String error() {
        return error;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public String path() {
        return path;
    }

    @Override
    public Set<FieldErrorDto> errors() {
        return errors;
    }
}
