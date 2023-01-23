package com.dh.clinicaOdontologica.config;

import com.dh.clinicaOdontologica.repository.UsuarioRepository;
import com.dh.clinicaOdontologica.entities.Usuario;
import com.dh.clinicaOdontologica.entities.UsuarioRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {
    private UsuarioRepository usuarioRepository;
    @Autowired
    public DataLoader(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
        String hash=passwordEncoder.encode("1234");
        Usuario Admin=new Usuario("Gonzalo Volpe","gonzav90","gonzalovolpe@hotmail.com",
                hash, UsuarioRole.ROLE_ADMIN);
        usuarioRepository.save(Admin);
        System.out.println("PRUEBA");

        String hash2 = passwordEncoder.encode("1234");
        Usuario usuario=new Usuario("Juan Perez","jperez","jperez@a.com"
                ,hash2, UsuarioRole.ROLE_USER);
        usuarioRepository.save(usuario);
    }
}
