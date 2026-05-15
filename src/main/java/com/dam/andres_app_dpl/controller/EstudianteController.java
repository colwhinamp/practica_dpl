package com.dam.andres_app_dpl.controller;

import com.dam.andres_app_dpl.model.Estudiante;
import com.dam.andres_app_dpl.service.EstudianteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService service;

    @PostMapping
    public Estudiante crear(@Valid @RequestBody Estudiante estudiante) {
        return service.guardarEstudiante(estudiante);
    }

    @GetMapping
    public List<Estudiante> listarTodos() {
        return service.obtenerTodos();
    }

    // Endpoint para actualizar (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> actualizar(@PathVariable Long id, @Valid @RequestBody Estudiante datos) {
        return service.obtenerPorId(id)
                .map(estudianteExistente -> {
                    estudianteExistente.setNombre(datos.getNombre());
                    estudianteExistente.setEmail(datos.getEmail());
                    estudianteExistente.setEdad(datos.getEdad());
                    return ResponseEntity.ok(service.guardarEstudiante(estudianteExistente));
                })
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
    }

    // Endpoint para borrar (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(estudiante -> {
                    service.eliminarEstudiante(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
    }
}