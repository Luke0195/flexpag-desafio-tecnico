package br.com.flexpag.app.dtos.response;

import br.com.flexpag.app.entities.enums.PaymentStatus;

import java.time.Instant;
import java.time.LocalDateTime;

public record AppointmentResponseDto(Long id, LocalDateTime date, String status, Instant createdAt, Instant updatedAt) {

    @Override
    public Long id() {
        return id;
    }

    @Override
    public LocalDateTime date() {
        return date;
    }

    @Override
    public String status() {
        return status;
    }

    @Override
    public Instant createdAt() {
        return createdAt;
    }

    @Override
    public Instant updatedAt() {
        return updatedAt;
    }
}
