package com.alura.ForoHub.infra;

import com.alura.ForoHub.repository.UsuarioRepository;
import com.alura.ForoHub.infra.security.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(SecurityFilter.class);

    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    public SecurityFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            // Obtener el header Authorization
            String authHeader = request.getHeader("Authorization");

            // Validar que el token esté presente y no sea vacío
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                logger.debug("Token no presente o mal formado.");
                filterChain.doFilter(request, response);
                return; // Continuar con la cadena de filtros
            }

            String token = authHeader.replace("Bearer ", "").trim();
            if (token.isEmpty()) {
                logger.debug("Token vacío después de eliminar el prefijo.");
                filterChain.doFilter(request, response);
                return;
            }

            logger.debug("Token recibido en el filtro: {}", token);

            // Validar el token y obtener el login del usuario
            String loginUsuario = tokenService.getSubject(token);
            logger.debug("Token válido. Login: {}", loginUsuario);

            if (loginUsuario != null) {
                // Cargar el usuario desde la base de datos
                var usuario = usuarioRepository.findByLogin(loginUsuario)
                        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

                // Configurar la autenticación en el contexto de seguridad
                var authentication = new UsernamePasswordAuthenticationToken(
                        usuario,
                        null,
                        usuario.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            // Limpiar el contexto si ocurre un error
            SecurityContextHolder.clearContext();
            logger.error("Error al validar el token: {}", e.getMessage(), e);
        }

        // Continuar con el siguiente filtro
        filterChain.doFilter(request, response);
    }
}


