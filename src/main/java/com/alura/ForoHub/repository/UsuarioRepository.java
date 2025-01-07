//package com.alura.ForoHub.repository;
//
//import com.alura.ForoHub.model.Usuario;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//@Repository
//public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
//    Optional<Usuario> findByLogin(String login); // Define el método personalizado
//}
//

package com.alura.ForoHub.repository;

import com.alura.ForoHub.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByLogin(String login);

}
