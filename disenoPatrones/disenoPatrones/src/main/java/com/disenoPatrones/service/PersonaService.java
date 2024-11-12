package com.disenoPatrones.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.disenoPatrones.entity.usuario;
import com.disenoPatrones.repository.UsuarioRepository;

@Service
public class PersonaService {

    @Autowired
    private UsuarioRepository personaRepository;

    public List<usuario> getAllPersonas() {
        return personaRepository.findAll();
    }

    public usuario guardar(usuario usuario) {
        return personaRepository.save(usuario);
    }

    public usuario getPersonaById(Integer id) {
        return personaRepository.findById(id).get();
    }

    public void deletePersona(Integer id) {
        personaRepository.deleteById(id);
    }
    
}
