package com.disenoPatrones.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.disenoPatrones.entity.usuario;

public interface UsuarioRepository extends JpaRepository<usuario, Integer> {

    Optional<usuario> findByEmail(String email);

    
}
