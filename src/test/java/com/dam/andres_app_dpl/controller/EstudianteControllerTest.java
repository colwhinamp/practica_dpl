package com.dam.andres_app_dpl.controller;

import com.dam.andres_app_dpl.model.Estudiante;
import com.dam.andres_app_dpl.service.EstudianteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class EstudianteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EstudianteService service;

    @InjectMocks
    private EstudianteController estudianteController;

    @BeforeEach
    void setUp() {
        // Configuramos MockMvc manualmente sin usar anotaciones de Spring Boot Test
        this.mockMvc = MockMvcBuilders.standaloneSetup(estudianteController).build();
    }

    @Test
    void testListarTodosEndpoint() throws Exception {
        Estudiante e = new Estudiante();
        e.setNombre("Ana");

        when(service.obtenerTodos()).thenReturn(List.of(e));

        mockMvc.perform(get("/api/estudiantes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].nombre").value("Ana"));
    }
}