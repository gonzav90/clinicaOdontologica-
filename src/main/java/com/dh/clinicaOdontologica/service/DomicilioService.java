package com.dh.clinicaOdontologica.service;

import com.dh.clinicaOdontologica.entities.Domicilio;

import java.util.List;
import java.util.Optional;

public interface DomicilioService {
    List<Domicilio> listarDomicilios();
    Optional<Domicilio> buscarDomicilio(Long id);
    Domicilio guardarDomicilio(Domicilio domicilio);
    Domicilio actualizarDomicilio(Domicilio domicilio);
    void eliminarDomicilio(Long id);
}
