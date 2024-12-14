package br.com.flexpag.app.dtos.response;

public record FieldErrorDto(String name, String description) {
    @Override
    public String name() {
        return name;
    }

    @Override
    public String description() {
        return description;
    }
}
