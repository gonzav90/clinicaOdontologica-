package com.dh.clinicaOdontologica.service;

import com.dh.clinicaOdontologica.entities.Odontologo;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class OdontologoServiceImplTest {

    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    void guardarOdontologoTest() {

        Odontologo odontologo = new Odontologo();
        odontologo.setApellido("Volpe");
        odontologo.setNombre("Gonzalo");
        odontologo.setMatricula("12345");

        odontologoService.guardarOdontologo(odontologo);

        Optional<Odontologo> odontologo1= odontologoService.buscarOdontologo(1L);
        assertEquals(1, odontologo1.get().getId());
    }
    @Test
    @Order(2)
    void listarOdontologoTest(){

        Odontologo odontologo2 = new Odontologo();
        odontologo2.setApellido("Perez");
        odontologo2.setNombre("Juan");
        odontologo2.setMatricula("512");

        odontologoService.guardarOdontologo(odontologo2);

        Odontologo odontologo3 = new Odontologo();
        odontologo3.setApellido("Simpson");
        odontologo3.setNombre("Homero");
        odontologo3.setMatricula("23215");

        odontologoService.guardarOdontologo(odontologo3);
        List<Odontologo> odontologosRegistrados = odontologoService.listarOdontologos();
        assertTrue(odontologosRegistrados.size()>0);
    }
    @Test
    @Order(3)
    void actualizarOdontologoTest() {
        Optional<Odontologo> odontologoAActualizar = odontologoService.buscarOdontologo(1L);
        odontologoAActualizar.get().setNombre("Pepe");

        assertEquals("Pepe", odontologoAActualizar.get().getNombre());
    }

    @Test
    @Order(4)
    void eliminarOdontologoTest(){
        odontologoService.eliminarOdontologo(1L);

        assertTrue( odontologoService.listarOdontologos().size()==2);
    }
}