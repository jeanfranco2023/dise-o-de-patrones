package com.disenoPatrones.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.disenoPatrones.entity.producto;

public interface Productorepository extends JpaRepository<producto, Integer> {
    
}
