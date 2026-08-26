package com.blasalda.reservaciones.service;

import com.blasalda.commons.service.CrudService;
import com.blasalda.reservaciones.dto.ReservacionRequest;
import com.blasalda.reservaciones.dto.ReservacionResponse;

public interface ReservacionService extends CrudService<ReservacionRequest, ReservacionResponse> {
    ReservacionResponse obtenerReservacionSinEstado(Long id);

    void cambiarEstadoReservacion(Long id, Long idEstadoReserva);

}
