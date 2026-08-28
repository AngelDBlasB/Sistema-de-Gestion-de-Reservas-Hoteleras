package com.blasalda.commons.clients;

import com.blasalda.commons.dto.habitacion.HabitacionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "habitaciones")
public interface HabitacionClient {

    @GetMapping("/{id}")
    HabitacionResponse obtenerHabitacionActivaPorId(@PathVariable Long id);

    @GetMapping("/id-habitacion/{id}")
    HabitacionResponse obtenerHabitacionPorId(@PathVariable Long id);

    @PutMapping("/{idHabitacion}/estado/{idEstado}")
    void actualizarEstadoHabitacion(
            @PathVariable("idHabitacion") Long idHabitacion,
            @PathVariable("idEstado") Long idEstado
    );

}
