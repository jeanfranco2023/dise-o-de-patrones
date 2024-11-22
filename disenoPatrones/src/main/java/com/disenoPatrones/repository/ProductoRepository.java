package com.disenoPatrones.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.disenoPatrones.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    Optional<Producto> findByCodigoProducto(String codigoProducto);

    Optional<Producto> findByNombreProducto(String nombreProducto);

}
