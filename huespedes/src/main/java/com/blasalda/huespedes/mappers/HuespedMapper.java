package com.blasalda.huespedes.mappers;

import com.blasalda.commons.dto.huespedes.HuespedRequest;
import com.blasalda.commons.dto.huespedes.HuespedResponse;
import com.blasalda.commons.mapper.CommonMapper;
import com.blasalda.commons.utils.StringCustomUtils;
import com.blasalda.huespedes.entity.Huesped;
import com.blasalda.huespedes.enums.TipoDocumento;
import org.springframework.stereotype.Component;

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
                String.join(" ", entidad.getNombre(),
                        entidad.getApellidoPaterno(),
                        entidad.getApellidoMaterno()),
                String.join(" ",
                        StringCustomUtils.obtenerEdad(entidad.getFechaNacimiento()).toString(), "años"),
                entidad.getEmail(),
                entidad.getTelefono(),
                entidad.getTipoDocumento().descripcion,
                entidad.getDocumento(),
                entidad.getNacionalidad()
        );
    }
}
