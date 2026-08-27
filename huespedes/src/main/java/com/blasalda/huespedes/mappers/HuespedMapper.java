package com.blasalda.huespedes.mappers;

import com.blasalda.commons.dto.huespedes.HuespedRequest;
import com.blasalda.commons.dto.huespedes.HuespedResponse;
import com.blasalda.commons.mapper.CommonMapper;
import com.blasalda.huespedes.entity.Huesped;
import com.blasalda.commons.enums.TipoDocumento;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class HuespedMapper implements CommonMapper<HuespedRequest, HuespedResponse, Huesped> {
    @Override
    public Huesped requestAEntidad(HuespedRequest request) {

        return Huesped.crear(
                request.nombre(),
                request.apellidoPaterno(),
                request.apellidoMaterno(),
                request.fechaNacimiento(),
                request.email().trim(),
                request.telefono(),
                request.documento(),
                request.nacionalidad(),
                TipoDocumento.getTipoDocumento(request.tipoDocumento())
        );
    }

    @Override
    public HuespedResponse entidadAResponse(Huesped entidad) {
        return new HuespedResponse(
                entidad.getId(),
                entidad.getNombre(),
                entidad.getApellidoPaterno(),
                entidad.getApellidoMaterno(),
                entidad.getFechaNacimiento(),
                entidad.getEmail(),
                entidad.getTelefono(),
                entidad.getTipoDocumento(),
                entidad.getDocumento(),
                entidad.getNacionalidad()
        );
    }
}
