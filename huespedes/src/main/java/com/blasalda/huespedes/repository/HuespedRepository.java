package com.blasalda.huespedes.repository;

import com.blasalda.commons.enums.EstadoRegistro;
import com.blasalda.huespedes.entity.Huesped;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HuespedRepository extends JpaRepository<Huesped, Long> {

    List<Huesped> findByEstadoRegistro(EstadoRegistro estadoRegistro);

    Boolean existsByEmailAndEstadoRegistro(String email, EstadoRegistro estadoRegistro);

    Boolean existsByEmailAndEstadoRegistroAndIdNot(String email, EstadoRegistro estadoRegistro, Long id);

    Boolean existsByTelefonoAndEstadoRegistro(String telefono, EstadoRegistro estadoRegistro);

    Boolean existsByTelefonoAndEstadoRegistroAndIdNot(String telefono, EstadoRegistro estadoRegistro, Long id);

    Boolean existsByDocumentoAndEstadoRegistro(String documento, EstadoRegistro estadoRegistro);

    Boolean existsByDocumentoAndEstadoRegistroAndIdNot(String documento, EstadoRegistro estadoRegistro, Long id);

    Optional<Huesped> findByIdAndEstadoRegistro(Long id, EstadoRegistro estadoRegistro);
}
