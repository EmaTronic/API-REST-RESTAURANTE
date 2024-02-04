package com.example.RestauranteAppV1.Repositorio;


import com.example.RestauranteAppV1.Entidades.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisRepositorio extends JpaRepository<Pais, Long> {

}

