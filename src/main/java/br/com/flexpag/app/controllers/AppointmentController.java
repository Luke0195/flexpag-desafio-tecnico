package br.com.flexpag.app.controllers;

import br.com.flexpag.app.dtos.request.AppointmentRequestDto;
import br.com.flexpag.app.dtos.response.AppointmentResponseDto;
import br.com.flexpag.app.services.appointment.AppointmentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/appointments")
public class AppointmentController {
    @Autowired
    private  AppointmentService appointmentService;


    @PostMapping
    public ResponseEntity<AppointmentResponseDto> createAppointment(@Valid @RequestBody AppointmentRequestDto appointmentRequestDto){
        AppointmentResponseDto appointment = appointmentService.addAppointment(appointmentRequestDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{appointmentId}").buildAndExpand(appointment.id()).toUri();
        return ResponseEntity.created(uri).body(appointment);
    }

}
