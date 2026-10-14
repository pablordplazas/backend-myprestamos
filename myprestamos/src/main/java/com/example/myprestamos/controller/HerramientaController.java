package com.example.myprestamos.controller;

import com.example.myprestamos.model.Herramienta;
import com.example.myprestamos.service.HerramientaService; // Importación requerida
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/herramientas")
@CrossOrigin(origins = "*")
public class HerramientaController {

    private final HerramientaService herramientaService;

    // Inyección por constructor (Sin @Autowired)
    public HerramientaController(HerramientaService herramientaService) {
        this.herramientaService = herramientaService;
    }

    @PostMapping
    public ResponseEntity<Herramienta> registrar(@Valid @RequestBody Herramienta herramienta) {
        return ResponseEntity.status(HttpStatus.CREATED).body(herramientaService.registrarHerramienta(herramienta));
    }

    @GetMapping
    public ResponseEntity<List<Herramienta>> consultar() {
        return ResponseEntity.ok(herramientaService.consultarHerramientas());
    }
}