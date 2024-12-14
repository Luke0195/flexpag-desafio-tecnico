package br.com.flexpag.app.services.appointment;

import br.com.flexpag.app.dtos.request.AppointmentRequestDto;
import br.com.flexpag.app.dtos.response.AppointmentResponseDto;
import br.com.flexpag.app.entities.Appointment;
import br.com.flexpag.app.entities.enums.PaymentStatus;
import br.com.flexpag.app.repository.AppointmentRepository;
import br.com.flexpag.app.services.appointment.exceptions.BusinessException;
import br.com.flexpag.app.services.appointment.exceptions.EntityNotFoundException;
import br.com.flexpag.app.services.appointment.usecases.AddAppointmentUseCase;
import br.com.flexpag.app.services.appointment.usecases.LoadAppointmentUseCase;
import br.com.flexpag.app.services.appointment.usecases.RemoveAppointmentUseCase;
import br.com.flexpag.app.services.appointment.usecases.UpdateAppointmentUseCase;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public class AppointmentService implements AddAppointmentUseCase, LoadAppointmentUseCase, RemoveAppointmentUseCase, UpdateAppointmentUseCase {

    @Autowired
    private  AppointmentRepository appointmentRepository;

    @Override
    @Transactional
    public AppointmentResponseDto addAppointment(AppointmentRequestDto appointmentRequestDto) {
        Appointment appointment = new Appointment();
        appointment.setDate(appointmentRequestDto.date());
        appointment.setStatus(PaymentStatus.PENDING);
        appointment = appointmentRepository.save(appointment);
        return new AppointmentResponseDto(appointment.getId(), appointment.getDate(), appointment.getStatus()
                .toString().toLowerCase(), appointment.getCreatedAt(), appointment.getCreatedAt());
    }

    @Override
    public List<AppointmentResponseDto> loadAppointmentsByStatus(String status) {
        List<Appointment> appointments =  appointmentRepository.findAppointmentsByStatus(PaymentStatus.valueOf(status));
        return appointments.stream().map(x -> new AppointmentResponseDto(x.getId(), x.getDate(),
                x.getStatus().toString().toLowerCase(), x.getCreatedAt(), x.getCreatedAt())).toList();
    }

    @Override
    public void removeAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("The appointment id is not found!")) ;
        if(appointment.getStatus().toString().equalsIgnoreCase("paid")) throw  new BusinessException("Cannot delete a appointment is already paid");
        appointmentRepository.deleteById(appointment.getId());

    }

    @Override
    public AppointmentResponseDto updateAppointment(Long id, AppointmentRequestDto appointmentRequestDto) {
        Appointment appointment = appointmentRepository.getReferenceById(id);
        if(Objects.isNull(appointment.getId())) throw new EntityNotFoundException("The appointment id is not found");
        appointment.setDate(appointmentRequestDto.date());
        appointment = appointmentRepository.save(appointment);
        return new AppointmentResponseDto(appointment.getId(), appointment.getDate(), appointment.getStatus().toString().toLowerCase(), appointment.getCreatedAt(), appointment.getUpdatedAt());
    }
}
