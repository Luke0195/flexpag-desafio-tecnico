package br.com.flexpag.app.services.appointment;

import br.com.flexpag.app.dtos.request.AppointmentRequestDto;
import br.com.flexpag.app.dtos.response.AppointmentResponseDto;
import br.com.flexpag.app.entities.Appointment;
import br.com.flexpag.app.entities.enums.PaymentStatus;
import br.com.flexpag.app.repository.AppointmentRepository;
import br.com.flexpag.app.services.appointment.usecases.AddAppointmentUseCase;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AppointmentService implements AddAppointmentUseCase {

    @Autowired
    private  AppointmentRepository appointmentRepository;

    @Override
    @Transactional
    public AppointmentResponseDto addAppointment(AppointmentRequestDto appointmentRequestDto) {
        Appointment appointment = new Appointment();
        appointment.setDate(appointmentRequestDto.date());
        appointment.setStatus(PaymentStatus.PENDING);
        appointment = appointmentRepository.save(appointment);
        return new AppointmentResponseDto(appointment.getId(), appointment.getDate(), appointment.getStatus().toString().toLowerCase(), appointment.getCreatedAt(), appointment.getCreatedAt());
    }
}
