package com.alura.ForoHub.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncryptor {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("Contraseña encriptada para '1234qwas': " + encoder.encode("1234qwas"));
        System.out.println("Contraseña encriptada para 'admin1234': " + encoder.encode("admin1234"));
        System.out.println("Contraseña encriptada para '12carlos': " + encoder.encode("12carlos"));

    }
}
