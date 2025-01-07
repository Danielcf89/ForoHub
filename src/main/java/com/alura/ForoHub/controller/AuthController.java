//package com.alura.ForoHub.controller;
//
//import com.alura.ForoHub.model.Usuario;
//import com.alura.ForoHub.infra.security.DatosJWTToken;
//import com.alura.ForoHub.infra.security.TokenService;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/auth") // Cambiado a /auth
//public class AuthController {
//
//    private final AuthenticationManager authenticationManager;
//    private final TokenService tokenService;
//
//    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
//        this.authenticationManager = authenticationManager;
//        this.tokenService = tokenService;
//    }
//
//    @PostMapping
//    public DatosJWTToken autenticarUsuario(@RequestBody Usuario datosAutenticacion) {
//        // Crear un token de autenticación
//        var authenticationToken = new UsernamePasswordAuthenticationToken(
//                datosAutenticacion.getLogin(), // Cambiado de getNombre() a getLogin()
//                datosAutenticacion.getClave()
//        );
//
//        // Autenticar al usuario
//        Authentication authentication = authenticationManager.authenticate(authenticationToken);
//        var usuario = (Usuario) authentication.getPrincipal();
//
//        // Generar un token JWT
//        var tokenJWT = tokenService.generarToken(usuario);
//        return new DatosJWTToken(tokenJWT);
//    }
//}

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
@RequestMapping("/auth") // Cambiado a /auth
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.jwtService = jwtService;
    }

    @PostMapping
    public DatosJWTToken autenticarUsuario(@RequestBody Usuario datosAutenticacion) {
        // Crear un token de autenticación
        var authenticationToken = new UsernamePasswordAuthenticationToken(
                datosAutenticacion.getLogin(), // Cambiado de getNombre() a getLogin()
                datosAutenticacion.getClave()
        );

        // Autenticar al usuario
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        var usuario = (Usuario) authentication.getPrincipal();

        // Generar un token JWT
        var tokenJWT = tokenService.generarToken(usuario);
        return new DatosJWTToken(tokenJWT);
    }

    // Nuevo endpoint para generar un token genérico
    @PostMapping("/token")
    public String generateToken() {
        return jwtService.generateToken("Danielcf89"); // Sustituye por el nombre de usuario deseado
    }
}


