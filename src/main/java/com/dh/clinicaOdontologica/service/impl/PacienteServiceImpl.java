package com.dh.clinicaOdontologica.service.impl;

import com.dh.clinicaOdontologica.entities.Paciente;
import com.dh.clinicaOdontologica.repository.PacienteRepository;
import com.dh.clinicaOdontologica.service.PacienteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements PacienteService {

    private static final Logger logger = Logger.getLogger(PacienteServiceImpl.class);
    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public List<Paciente> listarPacientes() {
        logger.info("Se Inicio El Metodo Listar Pacientes");
        return pacienteRepository.findAll();
    }

    @Override
    public Optional<Paciente> buscarPaciente(Long id) {
        logger.info("Se Inicio El Metodo Buscar Paciente");
        return pacienteRepository.findById(id);
    }

    @Override
    public Paciente guardarPaciente(Paciente paciente) {
        logger.info("Se Inicio El Metodo Guardar Paciente");
        return pacienteRepository.save(paciente);
    }

    @Override
    public Paciente actualizarPaciente(Paciente paciente) {
        logger.info("Se Inicio El Metodo Actualizar Paciente");
        return pacienteRepository.save(paciente);
    }

    @Override
    public void eliminarPaciente(Long id) {
        logger.info("Se Inicio El Metodo Eliminar Paciente");
        pacienteRepository.deleteById(id);
    }
}
