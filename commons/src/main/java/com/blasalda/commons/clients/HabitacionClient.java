package com.blasalda.commons.clients;

import com.blasalda.commons.dto.habitacion.HabitacionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "habitaciones")
public interface HabitacionClient {

    @GetMapping("/{id}")
    HabitacionResponse obtenerHabitacionActivaPorId(@PathVariable Long id);

    @GetMapping("/id-habitacion/{id}")
    HabitacionResponse obtenerHabitacionPorId(@PathVariable Long id);

}
