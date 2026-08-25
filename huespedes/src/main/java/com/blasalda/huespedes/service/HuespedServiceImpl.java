package com.blasalda.huespedes.service;

import com.blasalda.commons.dto.huespedes.HuespedRequest;
import com.blasalda.commons.dto.huespedes.HuespedResponse;
import com.blasalda.commons.enums.EstadoRegistro;
import com.blasalda.commons.exceptions.RecursoNoEncontradoException;
import com.blasalda.huespedes.entity.Huesped;
import com.blasalda.huespedes.enums.TipoDocumento;
import com.blasalda.huespedes.mappers.HuespedMapper;
import com.blasalda.huespedes.repository.HuespedRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class HuespedServiceImpl implements HuespedService {

    private final HuespedRepository huespedRepository;

    private final HuespedMapper huespedMapper;

    @Override
    @Transactional(readOnly = true)
    public List<HuespedResponse> listar() {
        return huespedRepository.findByEstadoRegistro(EstadoRegistro.ACTIVO)
                .stream().map(huespedMapper::entidadAResponse)
                .toList();
    }

    @Override
    public HuespedResponse obtenerPorId(Long id) {
        return huespedMapper.entidadAResponse(obtenerHuespedOExcepcion(id));
    }

    @Override
    public HuespedResponse registrar(HuespedRequest request) {

        validarEmailUnico(request.email());

        validarTelefonolUnico(request.telefono());

        validarDocumentoUnico(request.documento());

        Huesped huesped = huespedMapper.requestAEntidad(request);

        huespedRepository.save(huesped);

        return huespedMapper.entidadAResponse(huesped);
    }

    @Override
    public HuespedResponse actualizar(HuespedRequest request, Long id) {

        validarEmailUnico(request.email());

        validarTelefonolUnico(request.telefono());

        validarDocumentoUnico(request.documento());

        Huesped huesped = obtenerHuespedOExcepcion(id);

        huesped.actualizar(
                request.nombre(),
                request.apellidoPaterno(),
                request.apellidoMaterno(),
                request.fechaNacimiento(),
                request.email(),
                request.telefono(),
                TipoDocumento.getTipoDocumento(request.tipoDocumento()),
                request.documento(),
                request.nacionalidad()
        );

        huespedRepository.save(huesped);

        return huespedMapper.entidadAResponse(huesped);
    }

    @Override
    public void eliminar(Long id) {
        Huesped huesped = obtenerHuespedOExcepcion(id);
        huesped.eliminar();
        huespedRepository.save(huesped);
    }

    private Huesped obtenerHuespedOExcepcion(Long id) {
        return huespedRepository.findById(id).orElseThrow(() -> new
                RecursoNoEncontradoException("Huésped no encontrado"));
    }

    private void validarEmailUnico(String email){
        if(huespedRepository.existsByEmailAndEstadoRegistro(email, EstadoRegistro.ACTIVO)){
            throw new IllegalArgumentException("El email ya está en uso");
        }
    }
    private void validarTelefonolUnico(String telefono){
        if(huespedRepository.existsByTelefonoAndEstadoRegistro(telefono, EstadoRegistro.ACTIVO)){
            throw new IllegalArgumentException("El teléfono ya está en uso");
        }
    }
    private void validarDocumentoUnico(String documento){
        if(huespedRepository.existsByDocumentoAndEstadoRegistro(documento, EstadoRegistro.ACTIVO)){
            throw new IllegalArgumentException("El documento ya está en uso");
        }
    }
}
