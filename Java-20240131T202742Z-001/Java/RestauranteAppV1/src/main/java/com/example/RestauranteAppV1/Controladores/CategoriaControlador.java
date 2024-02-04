package com.example.RestauranteAppV1.Controladores;

import com.example.RestauranteAppV1.Entidades.Categoria;
import com.example.RestauranteAppV1.Entidades.Producto;
import com.example.RestauranteAppV1.Servicio.CategoriaServicio;
import com.example.RestauranteAppV1.Servicio.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CategoriaControlador {

    @Autowired
    private CategoriaServicio categoriaServicio;

    public CategoriaControlador(CategoriaServicio categoriaServicio) {
        this.categoriaServicio = categoriaServicio;
    }

    @RequestMapping("/categorias")
    public String verListaCategorias(Model modelo) {
        List<Categoria> listaCategorias = categoriaServicio.obtenerTodasCategorias();
        modelo.addAttribute("listaCategorias", listaCategorias);
        return "categorias";

    }


    @GetMapping("/categorias/nuevaCategoria")
    public String mostrarFormularioDeRegistrarCategoria(Model modelo) {
        modelo.addAttribute("categoria", new Categoria());
        return "nueva_categoria";
    }

    @PostMapping("/categorias/guardarCategoria")
    public String guardarCategoria(@ModelAttribute("categoria") Categoria categoria) {
        categoriaServicio.guardarCategoria(categoria);
        return "redirect:/categorias";
    }

    @GetMapping("/categorias/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long id, Model modelo) {
        Categoria categoria = categoriaServicio.obtenerCategoriaPorId(id);
        modelo.addAttribute("categoria", categoria);
        return "editar_categoria";
    }

    @PostMapping("/categorias/editar/{id}")
    public String editarCategoria(@PathVariable("id") Long id, @ModelAttribute("categoria") Categoria categoria) {
        Categoria categoriaExistente = categoriaServicio.obtenerCategoriaPorId(id);
        categoriaExistente.setNombre(categoria.getNombre());
        // Actualizar otros campos si es necesario

        categoriaServicio.guardarCategoria(categoriaExistente);
        return "redirect:/categorias";
    }


    @Autowired
    ProductoServicio productoServicio;

    @GetMapping("/categorias/eliminar/{id}")
    public String eliminarCategoria(@PathVariable("id") Long id, Model modelo) {
        Categoria categoria = categoriaServicio.obtenerCategoriaPorId(id);

        if (categoria != null) {
            List<Producto> productosAsociados = productoServicio.obtenerProductosPorCategoria(categoria);

            if (productosAsociados.isEmpty()) {
                categoriaServicio.eliminarCategoria(id);
                return "redirect:/categorias";
            } else {
                modelo.addAttribute("error", "La categoría no se puede eliminar porque tiene productos asociados.");
            }
        } else {
            // Mostrar mensaje de error indicando que la categoría no existe
            return "redirect:/categorias?error=categoria_no_existe";
        }

        List<Categoria> listaCategorias = categoriaServicio.obtenerTodasCategorias();
        modelo.addAttribute("listaCategorias", listaCategorias);
        return "categorias";
    }


}
