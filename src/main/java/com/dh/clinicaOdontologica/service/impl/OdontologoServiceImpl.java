package com.dh.clinicaOdontologica.service.impl;

import com.dh.clinicaOdontologica.entities.Odontologo;
import com.dh.clinicaOdontologica.repository.OdontologoRepository;
import com.dh.clinicaOdontologica.service.OdontologoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoServiceImpl implements OdontologoService {

    private static final Logger logger = Logger.getLogger(OdontologoServiceImpl.class);
    @Autowired
    private OdontologoRepository odontologoRepository;

    @Override
    public List<Odontologo> listarOdontologos() {
        logger.info("Se Inicio El Metodo Listar Odontologos");
        return odontologoRepository.findAll();
    }

    @Override
    public Optional<Odontologo> buscarOdontologo(Long id) {
        logger.info("Se Inicio El Metodo Buscar Odontologo");
        return odontologoRepository.findById(id);
    }

    @Override
    public Odontologo guardarOdontologo(Odontologo odontologo) {
        logger.info("Se Inicio El Metodo Guardar Odontologo");
        return odontologoRepository.save(odontologo);
    }

    @Override
    public Odontologo actualizarOdontologo(Odontologo odontologo) {
        logger.info("Se Inicio El Metodo Actualizar Odontologo");
        return odontologoRepository.save(odontologo);
    }

    @Override
    public void eliminarOdontologo(Long id) {
        logger.info("Se Inicio El Metodo Eliminar Odontologo");
        odontologoRepository.deleteById(id);
    }
}
