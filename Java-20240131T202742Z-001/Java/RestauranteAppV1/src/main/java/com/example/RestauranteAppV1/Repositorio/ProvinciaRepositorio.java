package com.example.RestauranteAppV1.Repositorio;

import com.example.RestauranteAppV1.Entidades.Provincia;
import com.example.RestauranteAppV1.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinciaRepositorio extends JpaRepository<Provincia, Long> {
    List<Provincia> findByPais_PaisId(Long paisId);
}
