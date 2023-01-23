package com.dh.clinicaOdontologica.repository;

import com.dh.clinicaOdontologica.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente,Long> {
}
