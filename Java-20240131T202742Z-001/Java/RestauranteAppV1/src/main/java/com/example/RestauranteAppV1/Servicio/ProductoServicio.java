package com.example.RestauranteAppV1.Servicio;

import com.example.RestauranteAppV1.Entidades.Categoria;
import com.example.RestauranteAppV1.Entidades.Producto;
import com.example.RestauranteAppV1.Repositorio.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServicio {
@Autowired
private ProductoRepositorio productoRepositorio;

public List<Producto> obtenerProductos(String palabraClave){
    if(palabraClave != null){
        return productoRepositorio.findAll(palabraClave);
    }
return productoRepositorio.findAll();
}
public List<Producto> obtenerTodosProductos(){
    return productoRepositorio.findAll();
}

    public List<Producto> obtenerProductosDisponibles() {
        return productoRepositorio.findAllDisponibles();
    }

public void guardarProducto(Producto producto){
    productoRepositorio.save(producto);
}

public Producto obtenerIdProducto(int id){
    return productoRepositorio.findById(id).get();
}

public void eliminarProducto(int id){
    productoRepositorio.deleteById(id);
}


    public List<Producto> obtenerProductosPorCategoria(Categoria categoria) {
        return productoRepositorio.findByCategoria(categoria);
    }





}

