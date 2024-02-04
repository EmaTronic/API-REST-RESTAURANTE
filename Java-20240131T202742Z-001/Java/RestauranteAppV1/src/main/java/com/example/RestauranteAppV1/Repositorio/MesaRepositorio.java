package com.example.RestauranteAppV1.Repositorio;

import com.example.RestauranteAppV1.Entidades.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MesaRepositorio extends JpaRepository<Mesa, Long> {
    List<Mesa> findByEstado(String estado);
}

