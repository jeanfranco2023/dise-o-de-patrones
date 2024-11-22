package com.disenoPatrones.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.disenoPatrones.entity.Producto;
import com.disenoPatrones.repository.ProductoRepository;
@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> obtenerTodosProductos() {
        return productoRepository.findAll();
    }

    public Producto obtenerProductoPorId(int id) {
        return productoRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }
}
