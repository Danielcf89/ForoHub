package com.alura.ForoHub.service;

import com.alura.ForoHub.repository.UsuarioRepository;
import com.alura.ForoHub.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario registrarUsuario(Usuario usuario) {
        // Guarda el usuario en la base de datos
        return usuarioRepository.save(usuario);
    }
}

