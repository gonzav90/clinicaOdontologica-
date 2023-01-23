package com.dh.clinicaOdontologica.service;

import com.dh.clinicaOdontologica.entities.Odontologo;
import com.dh.clinicaOdontologica.entities.Paciente;
import com.dh.clinicaOdontologica.entities.Turno;
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
class TurnoServiceImplTest {

    @Autowired
    private TurnoService turnoService;

    @Autowired
    private OdontologoService odontologoService;

    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    void guardarTurnoTest(){

        Odontologo odontologo = new Odontologo();
        odontologo.setApellido("Volpe");
        odontologo.setNombre("Gonzalo");
        odontologo.setMatricula("2163556");
        odontologoService.guardarOdontologo(odontologo);

        Paciente paciente = new Paciente();
        paciente.setApellido("Juan");
        paciente.setNombre("Perez");
        paciente.setEmail("jp@gmail.com");
        paciente.setDni(12358);
        pacienteService.guardarPaciente(paciente);

        Turno turno = new Turno();
        turno.setOdontologo(odontologo);
        turno.setPaciente(paciente);
        LocalDate fecha= LocalDate.of(2022,12,16);
        turno.setFecha(fecha);
        turnoService.guardarTurno(turno);

        Optional<Turno> turno1 = turnoService.buscarTurno(1L);
        assertEquals(1, turno1.get().getId());
    }

    @Test
    @Order(2)
    void ListarTurnosTest(){

        Odontologo odontologo2 = new Odontologo();
        odontologo2.setApellido("Volpe");
        odontologo2.setNombre("Francisco");
        odontologo2.setMatricula("5215");
        odontologoService.guardarOdontologo(odontologo2);

        Paciente paciente2 = new Paciente();
        paciente2.setApellido("Volpe");
        paciente2.setNombre("Edgardo");
        paciente2.setEmail("ev@gmail.com");
        paciente2.setDni(95030089);
        pacienteService.guardarPaciente(paciente2);

        Turno turno2 = new Turno();
        turno2.setOdontologo(odontologo2);
        turno2.setPaciente(paciente2);
        LocalDate fecha= LocalDate.of(2022,12,24);
        turno2.setFecha(fecha);
        turnoService.guardarTurno(turno2);

        Odontologo odontologo3 = new Odontologo();
        odontologo3.setApellido("Simpson");
        odontologo3.setNombre("Homero");
        odontologo3.setMatricula("123856");
        odontologoService.guardarOdontologo(odontologo3);

        Paciente paciente3 = new Paciente();
        paciente3.setApellido("Barney");
        paciente3.setNombre("Stinson");
        paciente3.setEmail("bs@gmail.com");
        paciente3.setDni(52156);
        pacienteService.guardarPaciente(paciente3);

        Turno turno3 = new Turno();
        turno3.setOdontologo(odontologo3);
        turno3.setPaciente(paciente3);
        fecha = LocalDate.of(2022,8,8);
        turno3.setFecha(fecha);
        turnoService.guardarTurno(turno3);

        List<Turno> turnosRegistrados = turnoService.listarTurnos();
        assertTrue(turnosRegistrados.size()>0);
    }

    @Test
    @Order(3)
    void actualizarTurnoTest(){
        Optional<Turno> turnoAActualizar = turnoService.buscarTurno(1L);
        turnoAActualizar.get().setFecha(LocalDate.of(2022,12,22));

        assertEquals(LocalDate.of(2022,12,22), turnoAActualizar.get().getFecha());
    }

    @Test
    @Order(4)
    void eliminarTurnoTest(){
        turnoService.eliminarTurno(2L);

        assertTrue(turnoService.listarTurnos().size()==2);
    }


}