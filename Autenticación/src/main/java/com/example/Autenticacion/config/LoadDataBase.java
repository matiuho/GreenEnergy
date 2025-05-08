package com.example.Autenticacion.config;

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
    CommandLineRunner initDatabase(RolRepository rolRepo, UsuarioRepository userRepo){
        return args ->{
            if(rolRepo.count() == 0 && userRepo.count() == 0){
                Rol admin = new Rol();
                admin.setNombre("Administrador");;
                rolRepo.save(admin);

                Rol user = new Rol();
                user.setNombre("Usuario");
                rolRepo.save(user);

                userRepo.save(new Usuario(null, "matias", "1234", admin));
                userRepo.save(new Usuario(null, "samuel", "4321", admin));

                System.out.println("Datos iniciales cargados");
            }
            else{
                System.out.println("datos ya existentes. No se cargaron nuevos datos");
            }
        };
    }
}
