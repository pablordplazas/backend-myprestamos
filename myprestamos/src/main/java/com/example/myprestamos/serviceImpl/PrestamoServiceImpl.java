package com.example.myprestamos.serviceImpl;

import com.example.myprestamos.dto.PrestamoRequest;
import com.example.myprestamos.model.Herramienta;
import com.example.myprestamos.model.Prestamo;
import com.example.myprestamos.repository.HerramientaRepository;
import com.example.myprestamos.repository.PrestamoRepository;
import com.example.myprestamos.service.PrestamoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PrestamoServiceImpl implements PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final HerramientaRepository herramientaRepository;

    public PrestamoServiceImpl(PrestamoRepository prestamoRepository, HerramientaRepository herramientaRepository) {
        this.prestamoRepository = prestamoRepository;
        this.herramientaRepository = herramientaRepository;
    }

    @Override
    @Transactional
    public Prestamo registrarPrestamo(PrestamoRequest request) {
        if (request.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad prestada debe ser superior a cero");
        }

        Herramienta herramienta = herramientaRepository.findById(request.getHerramientaId())
                .orElseThrow(() -> new RuntimeException("La herramienta con ID " + request.getHerramientaId() + " no existe"));

        if (herramienta.getCantidad() < request.getCantidad()) {
            throw new IllegalArgumentException("Unidades insuficientes. Disponibles: " + herramienta.getCantidad());
        }

        // Descontar inventario
        herramienta.setCantidad(herramienta.getCantidad() - request.getCantidad());
        herramientaRepository.save(herramienta);

        // Registrar préstamo
        Prestamo prestamo = new Prestamo();
        prestamo.setHerramienta(herramienta);
        prestamo.setResponsable(request.getResponsable());
        prestamo.setFechaPrestamo(request.getFechaPrestamo());
        prestamo.setCantidad(request.getCantidad());

        return prestamoRepository.save(prestamo);
    }

    @Override
    public List<Prestamo> consultarTodos() {
        return prestamoRepository.findAll();
    }

    @Override
    public List<Prestamo> consultarPorHerramienta(Long herramientaId) {
        return prestamoRepository.findByHerramientaId(herramientaId);
    }
}