package com.blasalda.habitaciones.controller;

import com.blasalda.commons.controller.CommonController;
import com.blasalda.commons.dto.habitacion.HabitacionRequest;
import com.blasalda.commons.dto.habitacion.HabitacionResponse;
import com.blasalda.habitaciones.service.HabitacionService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class HabitacionController extends CommonController<HabitacionRequest, HabitacionResponse, HabitacionService> {

public HabitacionController(HabitacionService service) {
    super(service);
}

}

