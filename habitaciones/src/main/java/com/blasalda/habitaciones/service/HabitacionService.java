package com.blasalda.habitaciones.service;

import com.blasalda.commons.dto.habitacion.HabitacionRequest;
import com.blasalda.commons.dto.habitacion.HabitacionResponse;
import com.blasalda.commons.service.CrudService;
import com.blasalda.habitaciones.entity.Habitacion;

public interface HabitacionService extends CrudService<HabitacionRequest, HabitacionResponse> {

    HabitacionResponse obtenerHabitacionPorIdSinEstado(Long id);



}
