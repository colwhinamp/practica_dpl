package com.dam.andres_app_dpl.service;

import com.dam.andres_app_dpl.model.Estudiante;
import com.dam.andres_app_dpl.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository repository;

    public Estudiante guardarEstudiante(Estudiante estudiante) {
        return repository.save(estudiante);
    }

    public List<Estudiante> obtenerTodos() {
        return repository.findAll();
    }

    // Métodos nuevos para PUT y DELETE
    public Optional<Estudiante> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public void eliminarEstudiante(Long id) {
        repository.deleteById(id);
    }
}