package com.disenoPatrones.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.disenoPatrones.entity.Venta;
import com.disenoPatrones.repository.VentaRepository;

@Service
public class VentaService {
    @Autowired
    private VentaRepository ventaRepository;

    public void registrarVenta(Venta venta) {
        // Guardar la venta en la base de datos
        ventaRepository.save(venta);
        }

    public Venta findById(Integer id) {
        return ventaRepository.findById(id).orElse(null);
    }
        
}
