package com.example.Roles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Roles.model.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long>{

}
