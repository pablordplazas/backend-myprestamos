package com.example.myprestamos.serviceImpl;

import com.example.myprestamos.model.Herramienta;
import com.example.myprestamos.repository.HerramientaRepository;
import com.example.myprestamos.service.HerramientaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HerramientaServiceImpl implements HerramientaService {

    private final HerramientaRepository herramientaRepository;

    public HerramientaServiceImpl(HerramientaRepository herramientaRepository) {
        this.herramientaRepository = herramientaRepository;
    }

    @Override
    public Herramienta registrarHerramienta(Herramienta herramienta) {
        if (herramienta.getCantidad() != null && herramienta.getCantidad() < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa");
        }
        return herramientaRepository.save(herramienta);
    }

    @Override
    public List<Herramienta> consultarHerramientas() {
        return herramientaRepository.findAll();
    }
}