package com.blasalda.huespedes.controller;

import com.blasalda.commons.controller.CommonController;
import com.blasalda.commons.dto.huespedes.HuespedRequest;
import com.blasalda.commons.dto.huespedes.HuespedResponse;
import com.blasalda.huespedes.service.HuespedService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HuespedController extends CommonController<HuespedRequest, HuespedResponse, HuespedService> {
    public HuespedController(HuespedService service) {
        super(service);
    }
}
