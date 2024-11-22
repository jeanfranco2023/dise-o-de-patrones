package com.disenoPatrones.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.disenoPatrones.entity.usuario;

@Service
@Primary
public class UsuarioServiceProxy implements UsuarioService {

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    @Override
    public Optional<usuario> findByEmail(String email) {
        System.out.println("Validación o logging antes de llamar al servicio real");

        if (email == null || email.isEmpty()) {
            System.out.println("Email inválido");
            return Optional.empty();
        }

        Optional<usuario> result = usuarioServiceImpl.findByEmail(email);

        System.out.println("Consulta realizada");

        return result;
    }
    
}
