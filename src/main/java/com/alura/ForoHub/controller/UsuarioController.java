package com.alura.ForoHub.controller;

import com.alura.ForoHub.model.Usuario;
import com.alura.ForoHub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario usuario) {
        usuario.setClave(passwordEncoder.encode(usuario.getClave())); // Encripta la contraseña
        Usuario nuevoUsuario = usuarioRepository.save(usuario); // Guarda el usuario
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }
}

