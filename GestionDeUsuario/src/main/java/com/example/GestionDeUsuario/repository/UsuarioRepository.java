package com.example.GestionDeUsuario.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.GestionDeUsuario.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    //buscar por el nombre
    Usuario findByNombreIgnoreCase(String nombre);

}
