package com.example.myprestamos.repository;

import com.example.myprestamos.model.Herramienta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HerramientaRepository extends JpaRepository<Herramienta, Long> {
}