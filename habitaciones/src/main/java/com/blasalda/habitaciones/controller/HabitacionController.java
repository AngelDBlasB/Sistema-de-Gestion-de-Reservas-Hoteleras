package com.blasalda.habitaciones.controller;

import com.blasalda.commons.controller.CommonController;
import com.blasalda.commons.dto.habitacion.HabitacionRequest;
import com.blasalda.commons.dto.habitacion.HabitacionResponse;
import com.blasalda.habitaciones.service.HabitacionService;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class HabitacionController extends CommonController<HabitacionRequest, HabitacionResponse, HabitacionService> {

    public HabitacionController(HabitacionService service) {
        super(service);
    }

    @GetMapping("/id-habitacion/{id}")
    public ResponseEntity<HabitacionResponse> obtenerHabitacionPorIdSinEstado(
            @PathVariable @Positive(message = "El ID debe ser positivo") Long id
    ){
        return  ResponseEntity.ok(service.obtenerHabitacionPorIdSinEstado(id));
    }

    @PutMapping("/{idHabitacion}/estado/{idEstado}")
    public ResponseEntity<Void> actualizarEstadoHabitacion(
            @PathVariable @Positive(message = "El ID deber ser positivo") Long idHabitacion,
            @PathVariable @Positive(message = "El idEstado deber ser positivo") Long idEstado
    ){
        service.actualizarEstadoHabitacion(idHabitacion, idEstado);
        return ResponseEntity.noContent().build();
    }

}

