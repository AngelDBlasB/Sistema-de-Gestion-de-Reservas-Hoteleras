package com.blasalda.huespedes.service;

import com.blasalda.commons.dto.huespedes.HuespedRequest;
import com.blasalda.commons.dto.huespedes.HuespedResponse;
import com.blasalda.commons.service.CrudService;

public interface HuespedService extends CrudService<HuespedRequest, HuespedResponse> {

    HuespedResponse obtenerPorIdSinEstado(Long id);

}
