package br.com.flexpag.app.dtos.request;

import jakarta.validation.constraints.NotNull;


import java.time.LocalDateTime;


public record AppointmentRequestDto(
        @NotNull(message = "The field date must be required")
        LocalDateTime date) {

    @Override
    public LocalDateTime date() {
        return date;
    }
}
