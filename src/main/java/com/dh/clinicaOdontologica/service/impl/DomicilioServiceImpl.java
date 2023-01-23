package com.dh.clinicaOdontologica.service.impl;

import com.dh.clinicaOdontologica.entities.Domicilio;
import com.dh.clinicaOdontologica.repository.DomicilioRepository;
import com.dh.clinicaOdontologica.service.DomicilioService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DomicilioServiceImpl implements DomicilioService {

    private static final Logger logger = Logger.getLogger(DomicilioServiceImpl.class);
    @Autowired
    private DomicilioRepository domicilioRepository;

    @Override
    public List<Domicilio> listarDomicilios() {
        logger.info("Se Inicio El Metodo Listar Domicilios");
        return domicilioRepository.findAll();
    }

    @Override
    public Optional<Domicilio> buscarDomicilio(Long id) {
        logger.info("Se Inicio El Metodo Buscar Domicilios");
        return domicilioRepository.findById(id);
    }

    @Override
    public Domicilio guardarDomicilio(Domicilio domicilio) {
        logger.info("Se Inicio El Metodo Guardar Domicilio");
        return domicilioRepository.save(domicilio);
    }

    @Override
    public Domicilio actualizarDomicilio(Domicilio domicilio) {
        logger.info("Se Inicio El Metodo Actualizar Domicilio");
        return domicilioRepository.save(domicilio);
    }

    @Override
    public void eliminarDomicilio(Long id) {
        logger.info("Se Inicio El Metodo Eliminar Domicilio");
        domicilioRepository.deleteById(id);
    }
}
