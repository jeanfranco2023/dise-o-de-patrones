package com.disenoPatrones.service;

import java.util.Optional;


import com.disenoPatrones.entity.usuario;

public interface UsuarioService {
     Optional<usuario> findByEmail(String email);
}
