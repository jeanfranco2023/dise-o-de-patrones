package com.disenoPatrones.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.disenoPatrones.entity.usuario;
import com.disenoPatrones.repository.UsuarioRepository;

@Service

public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Optional<usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
    
}
