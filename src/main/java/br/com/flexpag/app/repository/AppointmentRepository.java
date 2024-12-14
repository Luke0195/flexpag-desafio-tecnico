package br.com.flexpag.app.repository;

import br.com.flexpag.app.entities.Appointment;
import br.com.flexpag.app.entities.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query(value = "SELECT obj FROM Appointment obj where obj.status = :status")
    List<Appointment> findAppointmentsByStatus(@Param("status")PaymentStatus status);
}
