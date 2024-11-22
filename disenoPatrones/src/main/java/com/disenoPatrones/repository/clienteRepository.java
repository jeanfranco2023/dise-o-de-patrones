package com.disenoPatrones.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.disenoPatrones.entity.cliente;

public interface clienteRepository extends JpaRepository<cliente, Integer> {
    Optional<cliente> findByDniCliente(String dniCliente);
    Optional<cliente> findByNombreCliente(String nombreCliente);
}
