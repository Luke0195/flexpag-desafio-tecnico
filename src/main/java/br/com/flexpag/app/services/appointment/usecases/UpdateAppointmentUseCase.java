package br.com.flexpag.app.services.appointment.usecases;

import br.com.flexpag.app.dtos.request.AppointmentRequestDto;
import br.com.flexpag.app.dtos.response.AppointmentResponseDto;

public interface UpdateAppointmentUseCase {

    AppointmentResponseDto updateAppointment(Long id, AppointmentRequestDto appointmentRequestDto);
}
