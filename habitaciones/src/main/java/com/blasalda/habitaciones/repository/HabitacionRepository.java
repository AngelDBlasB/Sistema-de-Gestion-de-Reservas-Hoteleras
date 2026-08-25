package com.blasalda.habitaciones.repository;

import com.blasalda.commons.enums.EstadoRegistro;
import com.blasalda.habitaciones.entity.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {

    List<Habitacion> findByEstadoRegistro(EstadoRegistro estadoRegistro);

    Optional<Habitacion> findByIdAndEstadoRegistro(Long id, EstadoRegistro estadoRegistro);

    boolean existsByNumeroHabitacionAndEstadoRegistro(Integer numeroHabitacion, EstadoRegistro estadoRegistro);

    boolean existsByNumeroHabitacionAndEstadoRegistroAndIdNot(Integer numeroHabitacion, EstadoRegistro estadoRegistro, Long id);

}
