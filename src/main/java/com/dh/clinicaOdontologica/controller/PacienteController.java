package com.dh.clinicaOdontologica.controller;

import com.dh.clinicaOdontologica.entities.Paciente;
import com.dh.clinicaOdontologica.exceptions.BadRequestException;
import com.dh.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.dh.clinicaOdontologica.service.PacienteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private static final Logger logger = Logger.getLogger(OdontologoController.class);
    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<Paciente>> listarPacientes() {
        List<Paciente> listaDePacientes = pacienteService.listarPacientes();
        if (listaDePacientes.size() == 0){
            logger.warn("Operacion con Exito. Pero hasta el Momento No Hay Registros de Pacientes.");
        } else {
            logger.info("Operacion con Exito. Se Han Registrado: " +listaDePacientes.size() + " Pacientes.");
        }
        return ResponseEntity.ok(listaDePacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> obtenerPacientePorId(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado=pacienteService.buscarPaciente(id);
        if(pacienteBuscado.isPresent()){
            logger.info("Operacion con Exito. Se Ejecuto la Consulta Buscar Paciente con ID:" +id+ ".");
            return ResponseEntity.ok(pacienteBuscado.get());
        }
        else{
            logger.error("ERROR: No Se Pudo Encontrar al Paciente Buscado");
            throw new ResourceNotFoundException("No se Encontro al Paciente con ID= "+id+ ". Por favor ingrese un ID correcto.");
        }
    }

    @PostMapping
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente) throws BadRequestException {
        if (paciente.getDni() == 0) {
            logger.error("ERROR: El DNI del Paciente No Puede Ser = 0");
            throw new BadRequestException("El DNI del Paciente No es Correcto. Por Favor Ingrese un DNI Valido");
        } else{
            logger.info("Operacion con Exito. Se Registro el Paciente: " + paciente.getApellido()
                    + " " + paciente.getNombre() + " con DNI: " + paciente.getDni());
            return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
        }
    }

    @PutMapping
    public ResponseEntity<Paciente> actualizarPaciente(@RequestBody Paciente paciente) throws ResourceNotFoundException  {
        Optional<Paciente> pacienteAActualizar=pacienteService.buscarPaciente(paciente.getId());
        if (pacienteAActualizar.isPresent()){
            logger.info("Operacion con Exito. Se Actualizo el Paciente con ID: " + pacienteAActualizar.get().getId());
            return ResponseEntity.ok(pacienteService.actualizarPaciente(paciente));
        } else{
            logger.error("ERROR: El Paciente No Existe.");
            throw new ResourceNotFoundException("El Paciente con ID: " + paciente.getId() + " No Existe. ¿Ingreso un ID Correcto?");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteAEliminar = pacienteService.buscarPaciente(id);
        if (pacienteAEliminar.isPresent()){
            pacienteService.eliminarPaciente(id);
            logger.info("Operacion con Exito. Se Elimino el Paciente con ID: "+id+ ".");
            return ResponseEntity.ok("El Paciente con ID: " +id+ " Se Elimino Correctamente.");
        } else{
            logger.error("ERROR: El Paciente No Existe.");
            throw new ResourceNotFoundException("No Se Encontro al Paciente con " +
                    " ID= "+id+" porque No Existe. Por Favor Ingrese un ID correcto.");
        }
    }
}
