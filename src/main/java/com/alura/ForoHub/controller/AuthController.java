package com.alura.ForoHub.controller;

import com.alura.ForoHub.model.Usuario;
import com.alura.ForoHub.infra.security.DatosJWTToken;
import com.alura.ForoHub.infra.security.TokenService;
import com.alura.ForoHub.service.JwtService; // Asegúrate de importar esta clase
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth") // Endpoint principal de autenticación
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final JwtService jwtService;

    // Constructor para inyectar dependencias
    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.jwtService = jwtService;
    }

    @PostMapping
    public DatosJWTToken autenticarUsuario(@RequestBody Usuario datosAutenticacion) {
        // Crear un token de autenticación con los datos recibidos
        var authenticationToken = new UsernamePasswordAuthenticationToken(
                datosAutenticacion.getLogin(),
                datosAutenticacion.getClave()
        );

        // Autenticar al usuario con AuthenticationManager
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        var usuario = (Usuario) authentication.getPrincipal();

        // Generar el token JWT con TokenService
        var tokenJWT = tokenService.generarToken(usuario);
        return new DatosJWTToken(tokenJWT);
    }

    // Endpoint adicional para generar un token genérico
    @PostMapping("/token")
    public String generateToken() {
        // Usar JwtService para generar un token de prueba
        return jwtService.generateToken("Danielcf89");
    }
}



