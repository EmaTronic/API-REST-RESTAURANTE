package com.example.RestauranteAppV1.Servicio;

import com.example.RestauranteAppV1.Entidades.Categoria;
import com.example.RestauranteAppV1.Repositorio.CategoriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoriaServicio {
@Autowired
private CategoriaRepositorio categoriaRepositorio;

    public List<Categoria> obtenerTodasCategorias() {
        return categoriaRepositorio.findAll();
    }

    public Categoria obtenerCategoriaPorId(Long categoriaId) {
        return categoriaRepositorio.findById(categoriaId)
                .orElseThrow(() -> new NoSuchElementException("No se encontró la categoría con ID: " + categoriaId));
    }

    public Categoria guardarCategoria(Categoria categoria) {
        return categoriaRepositorio.save(categoria);
    }

    public void eliminarCategoria(Long categoriaId) {
        categoriaRepositorio.deleteById(categoriaId);
    }
}
