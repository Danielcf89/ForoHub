package com.alura.ForoHub.controller;//package com.alura.ForoHub.controller;
//
//import com.alura.ForoHub.model.Topico;
//import com.alura.ForoHub.repository.TopicoRepository;
//import com.alura.ForoHub.service.TopicoService;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.web.PageableDefault;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/topicos")
//public class TopicoController {
//
//    @Autowired
//    private TopicoRepository topicoRepository;
//
//    @Autowired
//    private TopicoService topicoService;
//
//    // Registrar un nuevo tópico
//    @PostMapping
//    public ResponseEntity<Topico> registrarTopico(@RequestBody @Valid Topico topico) {
//        try {
//            Topico nuevoTopico = topicoService.registrarTopico(topico);
//            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTopico);
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
//        }
//    }
//
//    // Listar todos los tópicos
//    @GetMapping
//    public List<Topico> listarTopicos() {
//        return topicoRepository.findAll();
//    }
//
//    // Listar los primeros 10 tópicos ordenados por fecha de creación en orden ascendente
//    @GetMapping("/ordenados")
//    public List<Topico> listarTopicosOrdenados() {
//        return topicoRepository.findAll(Sort.by(Sort.Direction.ASC, "fechaCreacion"));
//    }
//
//    // Buscar tópicos por curso y año
//    @GetMapping("/buscar")
//    public List<Topico> buscarPorCursoYAnio(@RequestParam String curso, @RequestParam int anio) {
//        return topicoRepository.findByCursoAndAnio(curso, anio);
//    }
//
//    // Listar tópicos con paginación
//    @GetMapping("/paginados")
//    public Page<Topico> listarTopicosPaginados(
//            @PageableDefault(size = 10, sort = "fechaCreacion", direction = Sort.Direction.ASC) Pageable pageable) {
//        return topicoRepository.findAll(pageable);
//    }
//    // Endpoint para buscar un tópico por ID
//    @GetMapping("/{id}")
//    public ResponseEntity<Topico> buscarPorId(@PathVariable Long id) {
//        return topicoRepository.findById(id)
//                .map(topico -> ResponseEntity.ok(topico))
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    // Endpoint para actualizar un tópico
//    @PutMapping("/{id}")
//    public ResponseEntity<Topico> actualizarTopico(@PathVariable Long id, @RequestBody Topico topicoActualizado) {
//        return topicoRepository.findById(id)
//                .map(topico -> {
//                    // Actualizar los campos del tópico
//                    topico.setTitulo(topicoActualizado.getTitulo());
//                    topico.setMensaje(topicoActualizado.getMensaje());
//                    topico.setStatus(topicoActualizado.getStatus());
//                    topico.setAutor(topicoActualizado.getAutor());
//                    topico.setCurso(topicoActualizado.getCurso());
//
//                    // Guardar cambios en la base de datos
//                    Topico topicoGuardado = topicoRepository.save(topico);
//                    return ResponseEntity.ok(topicoGuardado);
//                })
//                .orElse(ResponseEntity.notFound().build());
//    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
//        if (topicoRepository.existsById(id)) {
//            topicoRepository.deleteById(id);
//            return ResponseEntity.noContent().build(); // Retorna un 204 No Content
//        }
//        return ResponseEntity.notFound().build(); // Retorna un 404 Not Found
//    }
//}

import com.alura.ForoHub.model.DatosRegistroTopico;
import com.alura.ForoHub.model.Topico;
import com.alura.ForoHub.model.Usuario;
import com.alura.ForoHub.repository.TopicoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoRepository topicoRepository;

    public TopicoController(TopicoRepository topicoRepository) {
        this.topicoRepository = topicoRepository;
    }

    @PostMapping
    public ResponseEntity<?> registrarTopico(@RequestBody DatosRegistroTopico topicoDTO, Authentication authentication) {
        // Verificar si hay un usuario autenticado
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no autenticado");
        }

        // Extraer el usuario autenticado
        var usuario = (Usuario) authentication.getPrincipal();

        // Crear un nuevo tópico
        var topico = new Topico();
        topico.setTitulo(topicoDTO.getTitulo());
        topico.setMensaje(topicoDTO.getMensaje());
        topico.setFechaCreacion(LocalDateTime.now());
        topico.setStatus(topicoDTO.getStatus());
        topico.setAutor(usuario.getLogin()); // Usar el login del usuario autenticado
        topico.setCurso(topicoDTO.getCurso());

        // Guardar el tópico en la base de datos
        topicoRepository.save(topico);

        return ResponseEntity.ok("Tópico registrado con éxito");
    }
}


