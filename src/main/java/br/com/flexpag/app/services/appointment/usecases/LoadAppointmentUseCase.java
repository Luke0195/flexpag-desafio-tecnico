package br.com.flexpag.app.services.appointment.usecases;

import br.com.flexpag.app.dtos.response.AppointmentResponseDto;

import java.util.List;

public interface LoadAppointmentUseCase {

    List<AppointmentResponseDto> loadAppointmentsByStatus(String status);
}
