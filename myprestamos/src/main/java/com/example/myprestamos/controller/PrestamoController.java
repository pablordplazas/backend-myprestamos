package com.example.myprestamos.controller;

import com.example.myprestamos.dto.PrestamoRequest;
import com.example.myprestamos.model.Prestamo;
import com.example.myprestamos.service.PrestamoService; // Importación agregada
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
@CrossOrigin(origins = "*")
public class PrestamoController {

    private final PrestamoService prestamoService;

    // Inyección por constructor (Sin @Autowired)
    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody PrestamoRequest request) {
        try {
            Prestamo nuevoPrestamo = prestamoService.registrarPrestamo(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPrestamo);
        } catch (RuntimeException e) { // Se simplifica la captura de excepciones
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Prestamo>> consultarTodos() {
        return ResponseEntity.ok(prestamoService.consultarTodos());
    }

    @GetMapping("/herramienta/{id}")
    public ResponseEntity<List<Prestamo>> consultarPorHerramienta(@PathVariable Long id) {
        return ResponseEntity.ok(prestamoService.consultarPorHerramienta(id));
    }
}