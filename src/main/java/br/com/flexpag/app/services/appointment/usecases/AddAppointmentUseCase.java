package br.com.flexpag.app.services.appointment.usecases;

import br.com.flexpag.app.dtos.request.AppointmentRequestDto;
import br.com.flexpag.app.dtos.response.AppointmentResponseDto;

public interface AddAppointmentUseCase {

    AppointmentResponseDto addAppointment(AppointmentRequestDto appointmentRequestDto);

}
