package com.dh.clinicaOdontologica.service;

import com.dh.clinicaOdontologica.entities.Domicilio;
import com.dh.clinicaOdontologica.entities.Paciente;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class PacienteServiceImplTest {

    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    void guardarPacienteTest(){

        Domicilio domicilio = new Domicilio();
        domicilio.setCalle("33");
        domicilio.setNumero(378);
        domicilio.setLocalidad("Mercedes");
        domicilio.setProvincia("Buenos Aires");

        Paciente paciente = new Paciente();
        paciente.setApellido("Volpe");
        paciente.setNombre("Gonzalo");
        paciente.setEmail("gonzalovolpe@hotmail.com");
        paciente.setDni(35361906);
        paciente.setFechaIngreso(LocalDate.of(2022,  12, 11));
        paciente.setDomicilio(domicilio);

        pacienteService.guardarPaciente(paciente);

        Optional<Paciente> paciente1 = pacienteService.buscarPaciente(1L);
         assertEquals(1, paciente1.get().getId());
    }

    @Test
    @Order(2)
    void listarPacienteTest(){

        Domicilio domicilio2 = new Domicilio();
        domicilio2.setCalle("Junin");
        domicilio2.setNumero(52);
        domicilio2.setLocalidad("Balvanera");
        domicilio2.setProvincia("Capital Federal");

        Paciente paciente2 = new Paciente();
        paciente2.setApellido("Edgardo");
        paciente2.setNombre("Volpe");
        paciente2.setEmail("ev@gmail.com");
        paciente2.setDni(123456);
        paciente2.setFechaIngreso(LocalDate.of(2022,  12, 11));
        paciente2.setDomicilio(domicilio2);

        pacienteService.guardarPaciente(paciente2);

        Domicilio domicilio3 = new Domicilio();
        domicilio2.setCalle("35");
        domicilio2.setNumero(81);
        domicilio2.setLocalidad("Mercedes");
        domicilio2.setProvincia("Buenos Aires");

        Paciente paciente3 = new Paciente();
        paciente3.setApellido("Volpe");
        paciente3.setNombre("francisco");
        paciente3.setEmail("fv@gmail.com");
        paciente3.setDni(1231565);
        paciente3.setFechaIngreso(LocalDate.of(2022,  12, 11));
        paciente3.setDomicilio(domicilio3);

        pacienteService.guardarPaciente(paciente3);
        List<Paciente> pacientesRegistrados = pacienteService.listarPacientes();
        assertTrue(pacientesRegistrados.size()>0);

    }

    @Test
    @Order(3)
    void actualizarPacienteTest() {
        Optional<Paciente> pacienteAActualizar = pacienteService.buscarPaciente(1L);
        pacienteAActualizar.get().setNombre("David");

        assertEquals("David", pacienteAActualizar.get().getNombre());
    }

    @Test
    @Order(4)
    void eliminarPacienteTest(){
        pacienteService.eliminarPaciente(1L);

        assertTrue( pacienteService.listarPacientes().size()==2);
    }

}