package com.example.GestionDeUsuario.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.GestionDeUsuario.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    //metodo para buscar con un nombre
    Usuario findByNombreIgnoreCase(String nombre);
    boolean existsByEmail(String email);
    Optional<Usuario> findByEmail(String email);


}
