package br.com.flexpag.app.controllers;

import br.com.flexpag.app.dtos.request.AppointmentRequestDto;
import br.com.flexpag.app.dtos.response.AppointmentResponseDto;
import br.com.flexpag.app.services.appointment.AppointmentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<AppointmentResponseDto>>findAllByStatus(
            @RequestParam(name = "status", defaultValue ="paid") String status
    ){
        List<AppointmentResponseDto> response = appointmentService.loadAppointmentsByStatus(status);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        appointmentService.removeAppointmentById(id);
        return ResponseEntity.noContent().build();
        
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AppointmentResponseDto> updateAppointment(@PathVariable Long id, @RequestBody AppointmentRequestDto appointmentRequestDto){
        AppointmentResponseDto appointment = appointmentService.updateAppointment(id, appointmentRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(appointment);
    }
}
