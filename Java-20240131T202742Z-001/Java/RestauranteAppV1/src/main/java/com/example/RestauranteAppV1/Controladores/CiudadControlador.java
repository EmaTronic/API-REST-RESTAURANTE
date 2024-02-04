package com.example.RestauranteAppV1.Controladores;

import com.example.RestauranteAppV1.Entidades.Ciudad;
import com.example.RestauranteAppV1.Entidades.Provincia;
import com.example.RestauranteAppV1.Servicio.CiudadServicio;
import com.example.RestauranteAppV1.Servicio.ProvinciaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ciudades")
public class CiudadControlador {

    @Autowired
    private CiudadServicio ciudadServicio;

    @Autowired
    private ProvinciaServicio provinciaServicio;

    @GetMapping
    public String verListaCiudades(Model modelo) {
        List<Ciudad> listaCiudades = ciudadServicio.obtenerTodasCiudades();
        modelo.addAttribute("listaCiudades", listaCiudades);
        return "ciudades";
    }
    @GetMapping("/porProvincia/{provinciaId}")
    @ResponseBody
    public List<Ciudad> obtenerCiudadesPorProvincia(@PathVariable Long provinciaId) {
        return ciudadServicio.obtenerCiudadesPorProvincia(provinciaId);
    }
    @GetMapping("/nuevaCiudad")
    public String mostrarFormularioDeRegistrarCiudad(Model modelo) {
        List<Provincia> listaProvincias = provinciaServicio.obtenerTodasProvincias();
        modelo.addAttribute("listaProvincias", listaProvincias);
        modelo.addAttribute("ciudad", new Ciudad());
        return "nueva_ciudad";
    }

    @PostMapping("/guardarCiudad")
    public String guardarCiudad(@ModelAttribute("ciudad") Ciudad ciudad) {
        ciudadServicio.guardarCiudad(ciudad);
        return "redirect:/ciudades";
    }

    @GetMapping("/editarCiudad/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long id, Model modelo) {
        Ciudad ciudad = ciudadServicio.obtenerCiudadPorId(id);
        List<Provincia> listaProvincias = provinciaServicio.obtenerTodasProvincias();
        modelo.addAttribute("ciudad", ciudad);
        modelo.addAttribute("listaProvincias", listaProvincias);
        return "editar_ciudad";
    }

    @PostMapping("/editarCiudad/{id}")
    public String editarCiudad(@PathVariable("id") Long id, @ModelAttribute("ciudad") Ciudad ciudad) {
        Ciudad ciudadExistente = ciudadServicio.obtenerCiudadPorId(id);
        ciudadExistente.setNombre(ciudad.getNombre());
        ciudadExistente.setProvincia(ciudad.getProvincia());

        ciudadServicio.guardarCiudad(ciudadExistente);
        return "redirect:/ciudades";
    }

    @GetMapping("/eliminarCiudad/{id}")
    public String eliminarCiudad(@PathVariable("id") Long id) {
        ciudadServicio.eliminarCiudad(id);
        return "redirect:/ciudades";
    }
}
