package com.blasalda.reservaciones.controller;

import com.blasalda.commons.controller.CommonController;
import com.blasalda.reservaciones.dto.ReservacionRequest;
import com.blasalda.reservaciones.dto.ReservacionResponse;
import com.blasalda.reservaciones.service.ReservacionService;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservacionController extends CommonController<ReservacionRequest, ReservacionResponse, ReservacionService> {
    public ReservacionController(ReservacionService service) {
        super(service);
    }

    @GetMapping("/reservacion-id/{id}")
    public ResponseEntity<ReservacionResponse> obtenerReservacionSinEstadoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(super.service.obtenerReservacionSinEstado(id));
    }

    @PatchMapping("/{idReserva}/estado/{idEstado}")
    public ResponseEntity<Void> cambiarEstadoReservacion(
            @PathVariable @Positive(message = "El ID de la reserva debe ser positivo") Long idReserva,
            @PathVariable @Positive(message = "El ID del estado debe ser positivo") Long idEstado
    ) {
        super.service.cambiarEstadoReservacion(idReserva, idEstado);
        return ResponseEntity.noContent().build();
    }
}
