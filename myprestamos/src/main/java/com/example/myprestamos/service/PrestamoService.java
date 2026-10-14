package com.example.myprestamos.service;

import com.example.myprestamos.dto.PrestamoRequest;
import com.example.myprestamos.model.Prestamo;
import java.util.List;

public interface PrestamoService {
    Prestamo registrarPrestamo(PrestamoRequest request);
    List<Prestamo> consultarTodos();
    List<Prestamo> consultarPorHerramienta(Long herramientaId);
}