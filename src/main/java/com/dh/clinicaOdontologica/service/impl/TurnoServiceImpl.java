package com.dh.clinicaOdontologica.service.impl;

import com.dh.clinicaOdontologica.entities.Turno;
import com.dh.clinicaOdontologica.repository.TurnosRepository;
import com.dh.clinicaOdontologica.service.TurnoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoServiceImpl implements TurnoService {

    private static final Logger logger = Logger.getLogger(TurnoServiceImpl.class);
    @Autowired
    private TurnosRepository turnosRepository;

    @Override
    public List<Turno> listarTurnos() {
        logger.info("Se Inicio El Metodo Listar Turnos");
        return turnosRepository.findAll();
    }
    @Override
    public Optional<Turno> buscarTurno(Long id) {
        logger.info("Se Inicio El Metodo Buscar Turno");
        return turnosRepository.findById(id);
    }
    @Override
    public Turno guardarTurno(Turno turno) {
        logger.info("Se Inicio El Metodo Guardar Turno");
        return turnosRepository.save(turno);
    }
    @Override
    public Turno actualizarTurno(Turno turno) {
        logger.info("Se Inicio El Metodo Actualizar Turno");
        return turnosRepository.save(turno);
    }
    @Override
    public void eliminarTurno(Long id) {
        logger.info("Se Inicio El Metodo Eliminar Turno");
        turnosRepository.deleteById(id);
    }
}
