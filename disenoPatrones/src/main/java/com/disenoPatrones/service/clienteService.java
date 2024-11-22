package com.disenoPatrones.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.disenoPatrones.entity.cliente;
import com.disenoPatrones.repository.clienteRepository;

@Service
public class clienteService {
    @Autowired
    private clienteRepository clienteRepository;
    
    public cliente findByDni(String dniCliente) {
        return clienteRepository.findByDniCliente(dniCliente).orElse(null);
    }
}
