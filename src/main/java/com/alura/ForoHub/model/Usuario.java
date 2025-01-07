
//package com.alura.ForoHub.model;
//
//import jakarta.persistence.*;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.List;
//
//@Entity
//@Table(name = "usuarios")
//public class Usuario implements UserDetails {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(unique = true, nullable = false)
//    private String login;
//
//    @Column(nullable = false)
//    private String clave;
//
//    // Constructor vacío
//    public Usuario() {
//    }
//
//    // Constructor con argumentos
//    public Usuario(Long id, String login, String clave) {
//        this.id = id;
//        this.login = login;
//        this.clave = clave;
//    }
//
//    // Getters y Setters
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getLogin() {
//        return login;
//    }
//
//    public void setLogin(String login) {
//        this.login = login;
//    }
//
//    public String getClave() {
//        return clave;
//    }
//
//    public void setClave(String clave) {
//        this.clave = clave;
//    }
//
//
//    // Métodos de la interfaz UserDetails
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        // Puedes agregar roles si necesitas; por ahora, está vacío
//        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
//    }
//
//    @Override
//    public String getPassword() {
//        return clave;
//    }
//
//    @Override
//    public String getUsername() {
//        return login;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true; // Cambia según tu lógica de negocio
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true; // Cambia según tu lógica de negocio
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true; // Cambia según tu lógica de negocio
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true; // Cambia según tu lógica de negocio
//    }
//
//    public String getNombre() {
//    }
//}

package com.alura.ForoHub.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String login;

    @Column(nullable = false)
    private String clave;

    // Constructor vacío
    public Usuario() {
    }

    // Constructor con argumentos
    public Usuario(Long id, String login, String clave) {
        this.id = id;
        this.login = login;
        this.clave = clave;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    // Métodos de la interfaz UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Puedes agregar roles si necesitas; por ahora, está vacío
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return clave;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Cambia según tu lógica de negocio
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Cambia según tu lógica de negocio
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Cambia según tu lógica de negocio
    }

    @Override
    public boolean isEnabled() {
        return true; // Cambia según tu lógica de negocio
    }
}



