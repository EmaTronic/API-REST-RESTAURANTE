package com.example.RestauranteAppV1.Repositorio;

import com.example.RestauranteAppV1.Entidades.Categoria;
import com.example.RestauranteAppV1.Entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Integer>{
    @Query("SELECT p FROM Producto p WHERE p.nombre LIKE %?1% OR p.descripcion LIKE %?1% OR p.categoria.nombre LIKE %?1%")
    public List<Producto> findAll(String palabraClave);

    /*
    @Query("SELECT p FROM Producto p WHERE p.categoria = :categoria")
    List<Producto> findByCategoria(@Param("categoria") Categoria categoria);
    */
    @Query("SELECT p FROM Producto p WHERE p.categoria = :categoria AND p.disponible = 1")
    List<Producto> findByCategoria(@Param("categoria") Categoria categoria);
    @Query("SELECT p FROM Producto p WHERE p.disponible = 1")
    List<Producto> findAllDisponibles();

}

