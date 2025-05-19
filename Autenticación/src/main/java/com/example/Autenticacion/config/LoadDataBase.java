package com.example.Autenticacion.config;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.Autenticacion.model.Rol;
import com.example.Autenticacion.model.Usuario;
import com.example.Autenticacion.repository.RolRepository;
import com.example.Autenticacion.repository.UsuarioRepository;

@Configuration
public class LoadDataBase {
    @Bean
public CommandLineRunner initDatabase(RolRepository rolRepo, UsuarioRepository userRepo) {
    return args -> {
        if (rolRepo.count() == 0 && userRepo.count() == 0) {

            Rol admin = new Rol("Administrador");
            Rol user = new Rol("Usuario");
            

            rolRepo.saveAll(List.of(admin, user));

            Usuario samuel = new Usuario("Samuel","Villanueva",LocalDate.parse("2004-08-08"),"1234",admin);

            userRepo.save(samuel);

            System.out.println("✅ Datos iniciales cargados");
        } else {
            System.out.println("ℹ️ Datos ya existentes. No se cargaron nuevos datos");
        }
    };
}


}
