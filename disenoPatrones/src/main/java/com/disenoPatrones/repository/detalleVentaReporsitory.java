package com.disenoPatrones.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.disenoPatrones.entity.DetalleVenta;

public interface detalleVentaReporsitory extends JpaRepository<DetalleVenta, Long> {
    
}
