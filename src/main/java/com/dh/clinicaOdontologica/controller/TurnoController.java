package com.dh.clinicaOdontologica.controller;

import com.dh.clinicaOdontologica.entities.Odontologo;
import com.dh.clinicaOdontologica.entities.Paciente;
import com.dh.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.dh.clinicaOdontologica.entities.Turno;
import com.dh.clinicaOdontologica.exceptions.BadRequestException;
import com.dh.clinicaOdontologica.service.TurnoService;
import com.dh.clinicaOdontologica.service.PacienteService;
import com.dh.clinicaOdontologica.service.OdontologoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private static final Logger logger = Logger.getLogger(OdontologoController.class);
    @Autowired
    private TurnoService turnoService;

    @Autowired
    private OdontologoService odontologoService;

    @Autowired
    private PacienteService pacienteService;


    @GetMapping
    public ResponseEntity<List<Turno>> listarTurnos() {
        List<Turno> listaDeTurnos = turnoService.listarTurnos();
        if (listaDeTurnos.size() == 0) {
            logger.warn("Operacion con Exito. Pero hasta el Momento No Hay Registros de Turnos.");
        } else {
            logger.info("Operacion con Exito. Se Han Registrado: " + listaDeTurnos.size() + " Turnos.");
        }
        return ResponseEntity.ok(listaDeTurnos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Turno> turnoBuscado = turnoService.buscarTurno(id);
        if (turnoBuscado.isPresent()) {
            logger.info("Operacion con Exito. Se Ejecuto la Consulta Buscar Turno con ID:" + id + " Paciente" +
                    turnoBuscado.get().getPaciente().getApellido() + " " + turnoBuscado.get().getPaciente().getNombre() +
                    " Odontologo: " + turnoBuscado.get().getOdontologo().getApellido() + " " +
                    turnoBuscado.get().getOdontologo().getNombre() + ".");
            return ResponseEntity.ok(turnoBuscado.get());
        } else {
            logger.error("ERROR: No Se Pudo Encontrar el Turno Buscado");
            throw new ResourceNotFoundException("No se encontró al Turno con ID: " + id +
                    ". Por favor ingrese un ID correcto.");
        }
    }

    @PostMapping
    public ResponseEntity<Turno> registrarTurno(@RequestBody Turno turno) throws BadRequestException {

        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(turno.getPaciente().getId());
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(turno.getOdontologo().getId());

        if (pacienteBuscado.isPresent() && odontologoBuscado.isPresent()) {
            if (turno.getFecha().isAfter(LocalDate.now())) {
                logger.info("Operacion con Exito. El Turno Se Asigno para el Dia: " + turno.getFecha().toString() +
                        " al Paciente: " + pacienteBuscado.get().getApellido() +
                        " " + pacienteBuscado.get().getNombre() + " con el Odontologo: " +
                        odontologoBuscado.get().getApellido() + " " + odontologoBuscado.get().getNombre() + ".");
                return ResponseEntity.ok(turnoService.guardarTurno(turno));
            } else if (turno.getFecha().isEqual(LocalDate.now())) {
                logger.warn("Operacion con Exito. ¡RECUERDE! El Turno Vence el Dia de Hoy.");
                return ResponseEntity.ok(turnoService.guardarTurno(turno));
            } else {
                logger.error("ERROR: La Fecha Asignada para El Turno ya Expiro.");
                throw new BadRequestException("La Fecha del Turno No es Valida, Ya Expiro.");
            }
        } else {
            if (pacienteBuscado.isPresent()) {
                logger.error("ERROR: El Paciente No Existe.");
                throw new BadRequestException("El Odontologo con ID: " + turno.getOdontologo().getId() + " No Existe." +
                        " Por favor ingrese un ID correcto.");
            } else if (odontologoBuscado.isPresent()) {
                logger.error("ERROR: El Odontologo No Existe.");
                throw new BadRequestException("El Paciente con ID: " + turno.getPaciente().getId() + " No Existe." +
                        " Por favor ingrese un ID correcto.");
            } else {
                logger.error("ERROR: No hay Registros del Odontologo ni del Paciente.");
                throw new BadRequestException("El Odontologo con ID: " + turno.getOdontologo().getId() +
                        " y el Paciente con ID: " + turno.getPaciente().getId() + " No Existen." +
                        " Por favor ingrese los IDs correctamente.");
            }
        }
    }

    @PutMapping
    public ResponseEntity<Turno> actualizarTurno(@RequestBody Turno turno) throws ResourceNotFoundException {
        Optional<Turno> turnoAActualizar = turnoService.buscarTurno(turno.getId());
        if (turnoAActualizar.isPresent()) {
            logger.info("Operacion con Exito. Se Actualizo el Turno con ID: " + turnoAActualizar.get().getId());
            return ResponseEntity.ok(turnoService.actualizarTurno(turno));
        } else {
            logger.error("ERROR: El turno No Existe");
            throw new ResourceNotFoundException("El Turno con ID: " + turno.getId() + " No Existe. ¿Ingreso un ID Correcto?");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Turno> turnoAEliminar = turnoService.buscarTurno(id);
        if (turnoAEliminar.isPresent()) {
            turnoService.eliminarTurno(id);
            logger.info("Operacion con Exito. Se Elimino el Turno con ID: "+id+ ".");
            return ResponseEntity.ok("El Turno con ID: " +id+ " Se Elimino Correctamente.");
        } else {
            logger.error("ERROR: El Turno No Existe.");
            throw new ResourceNotFoundException ("No Se Encontro el Turno con " +
                    " ID= "+id+" porque No Existe. Por Favor Ingrese un ID correcto.");
        }
    }
}

