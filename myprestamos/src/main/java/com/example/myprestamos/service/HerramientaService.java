package com.example.myprestamos.service;

import com.example.myprestamos.model.Herramienta;
import java.util.List;

public interface HerramientaService {
    Herramienta registrarHerramienta(Herramienta herramienta);
    List<Herramienta> consultarHerramientas();
}