package com.disenoPatrones.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.disenoPatrones.entity.Venta;

public interface VentaRepository extends JpaRepository<Venta, Integer> {

    
}
