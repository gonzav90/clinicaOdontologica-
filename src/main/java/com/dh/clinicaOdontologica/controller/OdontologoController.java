package com.dh.clinicaOdontologica.controller;

import com.dh.clinicaOdontologica.entities.Odontologo;
import com.dh.clinicaOdontologica.exceptions.BadRequestException;
import com.dh.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.dh.clinicaOdontologica.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    private static final Logger logger = Logger.getLogger(OdontologoController.class);
    @Autowired
    private OdontologoService odontologoService;

    @GetMapping
    public ResponseEntity<List<Odontologo>> listarOdontologos() {
        List<Odontologo> listaDeOdontologos =odontologoService.listarOdontologos();
        if (listaDeOdontologos.size() == 0){
            logger.warn("Operacion con Exito. Pero hasta el Momento No Hay Registros de Odontologos.");
        } else {
            logger.info("Operacion con Exito. Se Han Registrado: " +listaDeOdontologos.size() + " Odontologos.");
        }
        return ResponseEntity.ok(listaDeOdontologos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(id);
        if (odontologoBuscado.isPresent()) {
            logger.info("Operacion con Exito. Se Ejecuto la Consulta Buscar Odontologo con ID:" +id+ ".");
            return ResponseEntity.ok(odontologoBuscado.get());
        } else {
            logger.error("ERROR: No Se Pudo Encontrar al Odontologo Buscado");
            throw new ResourceNotFoundException("No Se Encontro al Odontologo con ID= " + id + ". Por favor ingrese un ID correcto.");
        }
    }

    @PostMapping()
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo) throws BadRequestException {
        if (odontologo.getMatricula() == "" || odontologo.getMatricula() == null) {
            logger.error("ERROR: La Matricula del Odontologo No Puede Estar Vacia");
            throw new BadRequestException("La Matricula No Puede Estar Vacia. Por Favor Ingrese una Matricula");
        } else {
            logger.info("Operacion con Exito. Se Registro el Odontologo: " + odontologo.getApellido()
                    + " " + odontologo.getNombre() + " con Matricula: " + odontologo.getMatricula());
            return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
        }
    }


    @PutMapping
    public ResponseEntity<Odontologo> actualizarOdontologo(@RequestBody Odontologo odontologo) throws ResourceNotFoundException{
        Optional<Odontologo> odontologoAActualizar= odontologoService.buscarOdontologo(odontologo.getId());
        if(odontologoAActualizar.isPresent()){
            logger.info("Operacion con Exito. Se Actualizo el Odontologo con ID: " + odontologoAActualizar.get().getId());
            return ResponseEntity.ok(odontologoService.actualizarOdontologo(odontologo));
        }
        else{
            logger.error("ERROR: El Odontologo No Existe.");
            throw new ResourceNotFoundException("El Odontologo con ID: " + odontologo.getId() + " No Existe. ¿Ingreso un ID Correcto?");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(id);
        if(odontologoBuscado.isPresent()){
            odontologoService.eliminarOdontologo(id);
            logger.info("Operacion con Exito. Se Elimino el Odontologo con ID: "+id+ ".");
            return ResponseEntity.ok("El Odontologo con ID: " +id+ " Se Elimino Correctamente.");
        } else {
            logger.error("ERROR: El Odontologo No Existe.");
            throw new ResourceNotFoundException("No Se Encontro al Odontologo con ID: "+id+" porque No Existe. " +
                    "Por favor Ingrese un ID correcto.");
        }
    }

}
