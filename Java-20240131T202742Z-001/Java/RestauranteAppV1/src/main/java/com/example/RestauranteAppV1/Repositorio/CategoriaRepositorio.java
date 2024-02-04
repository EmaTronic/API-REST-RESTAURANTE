package com.example.RestauranteAppV1.Repositorio;

import com.example.RestauranteAppV1.Entidades.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepositorio extends JpaRepository<Categoria, Long> {

}
