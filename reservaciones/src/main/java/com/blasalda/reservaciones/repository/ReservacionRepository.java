package com.blasalda.reservaciones.repository;

import com.blasalda.commons.enums.EstadoRegistro;
import com.blasalda.reservaciones.entity.Reservacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservacionRepository extends JpaRepository<Reservacion, Long> {
    List<Reservacion> findByEstadoRegistro(EstadoRegistro estadoRegistro);

    Optional<Reservacion> findByIdAndEstadoRegistro(Long id, EstadoRegistro estadoRegistro);
}
