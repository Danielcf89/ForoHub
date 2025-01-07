package com.alura.ForoHub.repository;

import com.alura.ForoHub.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {

    @Query("SELECT t FROM Topico t WHERE t.curso = :curso AND FUNCTION('YEAR', t.fechaCreacion) = :anio")
    List<Topico> findByCursoAndAnio(@Param("curso") String curso, @Param("anio") int anio);

    boolean existsByTituloAndMensaje(String titulo, String mensaje);
}


