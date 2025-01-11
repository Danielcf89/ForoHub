package com.alura.ForoHub.service;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;


@Service
public class JwtService {

    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    /**
     * Clave secreta, creada a partir de la propiedad inyectada.
     */
    private final Key secretKey;

    /**
     * Constructor que inicializa la clave secreta decodificando el valor Base64
     * desde application.properties.
     *
     * @param secretBase64 Clave secreta codificada en Base64.
     */
    public JwtService(@Value("${api.security.secret}") String secretBase64) {
        byte[] decodedKey = Base64.getDecoder().decode(secretBase64);
        this.secretKey = new SecretKeySpec(decodedKey, SignatureAlgorithm.HS256.getJcaName());
        logger.info("JWTService iniciado. Clave secreta configurada correctamente.");
    }

    /**
     * Genera un token JWT firmado con la clave secreta.
     *
     * @param username Nombre de usuario (subject) que irá en el token.
     * @return Token JWT firmado.
     */
    public String generateToken(String username) {
        String token = Jwts.builder()
                .setHeaderParam("typ", "JWT") // Agrega el campo "typ": "JWT" al encabezado
                .setSubject(username)
                .setIssuedAt(new Date()) // Fecha de emisión
                .setExpiration(new Date(System.currentTimeMillis() + 3600_000)) // Expira en 1 hora
                .signWith(secretKey) // Firma con la clave secreta
                .compact();
        logger.debug("Token generado para usuario '{}': {}", username, token);
        return token;
    }


    /**
     * Obtiene el 'subject' del token (generalmente, el nombre de usuario).
     *
     * @param token Token JWT.
     * @return Subject (username) si el token es válido.
     * @throws JwtException si el token es inválido o ha expirado.
     */
    public String getSubject(String token) {
        try {
            String subject = Jwts.parserBuilder()
                    .setSigningKey(secretKey) // Validación con la misma clave secreta
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            logger.debug("Token validado correctamente. Subject: {}", subject);
            return subject;
        } catch (JwtException e) {
            logger.error("Error al validar el token: {}", e.getMessage());
            throw e; // Relanza la excepción para que se maneje en el filtro o controlador
        }
    }
}



