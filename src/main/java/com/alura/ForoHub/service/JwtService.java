package com.alura.ForoHub.service;//package com.alura.ForoHub.service;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.function.Function;
//
//@Service
//public class JwtService {
//
//    // Clave secreta de al menos 256 bits (32 caracteres)
//    private final String SECRET_KEY = "ThisIsAStrongSecretKeyWithEnoughLength123!";
//
//    // Generar un token JWT
//    public String generateToken(String username) {
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 horas
//                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // Usar HS256 con una clave válida
//                .compact();
//    }
//
//    // Extraer el nombre de usuario del token
//    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    // Método genérico para extraer cualquier campo del token
//    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//
//    // Extraer todos los claims (campos) del token
//    private Claims extractAllClaims(String token) {
//        return Jwts.parser()
//                .setSigningKey(SECRET_KEY)
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    // Verificar si el token ha expirado
//    private boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//    // Extraer la fecha de expiración del token
//    private Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    // Validar si el token es válido usando UserDetails
//    public boolean isTokenValid(String token, UserDetails userDetails) {
//        final String username = extractUsername(token);
//        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
//    }
//
//    // Validar si el token es válido usando un username (String)
//    public boolean isTokenValid(String token, String username) {
//        final String extractedUsername = extractUsername(token);
//        return extractedUsername.equals(username) && !isTokenExpired(token);
//    }
//}
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtService {

    // Clave secreta proporcionada
    private final String SECRET = "hFqg8bAEJJM7mVZuPqgBfjHLVSGblYUCCZfamLuwSD8=";
    private final Key SECRET_KEY = new SecretKeySpec(Base64.getDecoder().decode(SECRET), SignatureAlgorithm.HS256.getJcaName());

    // Método para generar un token JWT
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date()) // Fecha de emisión
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Expira en 10 horas
                .signWith(SECRET_KEY) // Usar la clave secreta
                .compact();
    }

    // Método para obtener el subject (nombre de usuario) del token
    public String getSubject(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY) // Usa la misma clave para verificar
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (JwtException e) {
            System.err.println("Error al validar el token: " + e.getMessage());
            throw e; // Relanza la excepción para que el filtro la maneje
        }
    }

}




