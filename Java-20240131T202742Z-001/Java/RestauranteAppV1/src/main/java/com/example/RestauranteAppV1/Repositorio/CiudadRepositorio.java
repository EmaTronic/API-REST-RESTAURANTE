package com.example.RestauranteAppV1.Repositorio;

import com.example.RestauranteAppV1.Entidades.Ciudad;
import com.example.RestauranteAppV1.Entidades.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CiudadRepositorio extends JpaRepository<Ciudad, Long> {
    List<Ciudad> findByProvincia_ProvinciaId(Long provinciaId);

}
