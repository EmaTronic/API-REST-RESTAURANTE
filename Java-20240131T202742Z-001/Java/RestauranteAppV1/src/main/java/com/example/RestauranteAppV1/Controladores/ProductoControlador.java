package com.example.RestauranteAppV1.Controladores;

import com.example.RestauranteAppV1.Entidades.Categoria;
import com.example.RestauranteAppV1.Entidades.Producto;
import com.example.RestauranteAppV1.Repositorio.CategoriaRepositorio;
import com.example.RestauranteAppV1.Repositorio.ProductoRepositorio;
import com.example.RestauranteAppV1.Servicio.CategoriaServicio;
import com.example.RestauranteAppV1.Servicio.ProductoServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProductoControlador {
    @Autowired
    private ProductoServicio productoServicio;

    @RequestMapping("/productos")
    public String verPaginaDeInicio(Model modelo, @RequestParam(name = "palabraClave", required = false) String palabraClave, RedirectAttributes redirectAttributes){

        List<Producto> listaProductos = productoServicio.obtenerProductos(palabraClave);


        modelo.addAttribute("listaProductos", listaProductos);
        modelo.addAttribute("palabraClave", palabraClave);

        List<Categoria> listaCategorias = categoriaServicio.obtenerTodasCategorias();
        modelo.addAttribute("listaCategorias", listaCategorias);

        // Agregar mensaje de error si existe
        if (redirectAttributes.containsAttribute("error")) {
            modelo.addAttribute("error", redirectAttributes.getAttribute("error"));
        }
        return "productos";
    }

    @RequestMapping("/menu")
    public String verMenu(Model modelo) {
        List<Categoria> listaCategorias = categoriaServicio.obtenerTodasCategorias();
        modelo.addAttribute("listaCategorias", listaCategorias);

        // Obtener todos los productos
        List<Producto> listaProductos = productoServicio.obtenerProductos(null);
        modelo.addAttribute("listaProductos", listaProductos);

        return "menu";
    }

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Autowired
    private CategoriaServicio categoriaServicio;
    @RequestMapping("/nuevoProducto")
    public String mostrarFormularioDeRegistrarProducto(Model modelo){
        List<Categoria> listaCategorias = categoriaServicio.obtenerTodasCategorias();
        modelo.addAttribute("producto",new Producto());
        modelo.addAttribute("listaCategorias", listaCategorias);
        return "nuevo_producto";
    }
    @RequestMapping(value = "/guardarProducto", method = RequestMethod.POST)
    public String guardarProducto(@ModelAttribute("producto") Producto producto) {
        productoServicio.guardarProducto(producto);
        return "redirect:/productos";
    }

    @RequestMapping("/editarProducto/{producto_id}")
    public ModelAndView mostrarFormularioDeEdicionProducto(@PathVariable(name = "producto_id") Integer producto_id){
    ModelAndView modelo = new ModelAndView("editar_producto");

    Producto producto = productoServicio.obtenerIdProducto(producto_id);
        List<Categoria> listaCategorias = categoriaRepositorio.findAll();

    modelo.addObject("producto",producto);
        modelo.addObject("listaCategorias", listaCategorias);
        return modelo;
    }
    @RequestMapping("/eliminarProducto/{producto_id}")
    public String eliminarProducto(@PathVariable(name = "producto_id") Integer producto_id, RedirectAttributes redirectAttributes) {
        try {
            productoServicio.eliminarProducto(producto_id);
            return "redirect:/productos";
        } catch (Exception e) {
            // Manejo de error: agregar un mensaje de error a los atributos de redirección
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el producto. Asegúrate de que no esté asociado a ninguna orden.");
            return "redirect:/productos";
        }
    }


@Autowired
ProductoRepositorio productoRepositorio;

    @GetMapping("/productos/precio/{id}")
    @ResponseBody
    public Double obtenerPrecioProducto(@PathVariable("id") int id) {
        Producto producto = productoRepositorio.findById(id).orElse(null);
        if (producto != null) {
            return producto.getPrecio();
        } else {
            return null;
        }
    }



    @GetMapping("/productos/por-categoria/{categoriaId}")
    @ResponseBody
    public List<Producto> obtenerProductosPorCategoria(@PathVariable("categoriaId") Long categoriaId) {
        Categoria categoria = categoriaServicio.obtenerCategoriaPorId(categoriaId);
        return productoServicio.obtenerProductosPorCategoria(categoria);
    }

}
