package com.alura.ForoHub.service;

import com.alura.ForoHub.model.Topico;
import com.alura.ForoHub.repository.TopicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Transactional
    public Topico registrarTopico(Topico topico) {
        // Validación para evitar duplicados
        boolean existe = topicoRepository.existsByTituloAndMensaje(topico.getTitulo(), topico.getMensaje());
        if (existe) {
            throw new IllegalArgumentException("Ya existe un tópico con el mismo título y mensaje.");
        }

        // Establecer la fecha de creación
        topico.setFechaCreacion(LocalDateTime.now());

        // Guardar el tópico en la base de datos
        return topicoRepository.save(topico);
    }
}

