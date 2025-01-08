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
     * Clave secreta (ya decodificada).
     * Se construye en el constructor usando la propiedad inyectada.
     */
    private final Key secretKey;

    /**
     * Inyecta la propiedad desde application.properties:
     *   api.security.secret=hFqg8bAEJJM7mVZuPqgBfjHLVSGblYUCCZfamLuwSD8=
     * Luego decodifica la base64 y construye la Key.
     */
    public JwtService(@Value("${api.security.secret}") String secretBase64) {
        // Decodifica la cadena Base64 y crea la Key para HS256
        this.secretKey = new SecretKeySpec(
                Base64.getDecoder().decode(secretBase64),
                SignatureAlgorithm.HS256.getJcaName()
        );
        logger.info("JWTService iniciado. Se ha leído la clave desde properties.");
    }

    /**
     * Genera el token JWT con la clave generada arriba.
     */
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                // Ejemplo: expira en 1 hora
                .setExpiration(new Date(System.currentTimeMillis() + 3600_000))
                .signWith(secretKey)
                .compact();
    }

    /**
     * Obtiene el 'subject' del token (generalmente, el username).
     */
    public String getSubject(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey) // MISMA key de arriba
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (JwtException e) {
            logger.error("Error al validar el token: {}", e.getMessage());
            throw e; // Se relanza para que tu filtro o controlador la maneje
        }
    }
}



