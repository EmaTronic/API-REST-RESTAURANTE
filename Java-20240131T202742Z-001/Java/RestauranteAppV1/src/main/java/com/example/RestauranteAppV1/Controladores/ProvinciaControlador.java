package com.example.RestauranteAppV1.Controladores;

import com.example.RestauranteAppV1.Entidades.Pais;
import com.example.RestauranteAppV1.Entidades.Provincia;
import com.example.RestauranteAppV1.Servicio.PaisServicio;
import com.example.RestauranteAppV1.Servicio.ProvinciaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/provincias")
public class ProvinciaControlador {

    @Autowired
    private ProvinciaServicio provinciaServicio;

    @Autowired
    private PaisServicio paisServicio;

    @GetMapping
    public String verListaProvincias(Model modelo) {
        List<Provincia> listaProvincias = provinciaServicio.obtenerTodasProvincias();
        modelo.addAttribute("listaProvincias", listaProvincias);
        return "provincias";
    }

    @GetMapping("/porPais/{paisId}")
    @ResponseBody
    public List<Provincia> obtenerProvinciasPorPais(@PathVariable Long paisId) {
        return provinciaServicio.obtenerProvinciasPorPais(paisId);

    }

    @GetMapping("/nuevaProvincia")
    public String mostrarFormularioDeRegistrarProvincia(Model modelo) {
        List<Pais> listaPaises = paisServicio.obtenerTodosPaises();
        modelo.addAttribute("listaPaises", listaPaises);
        modelo.addAttribute("provincia", new Provincia());
        return "nueva_provincia";
    }

    @PostMapping("/guardarProvincia")
    public String guardarProvincia(@ModelAttribute("provincia") Provincia provincia) {
        provinciaServicio.guardarProvincia(provincia);
        return "redirect:/provincias";
    }

    @GetMapping("/editarProvincia/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long id, Model modelo) {
        Provincia provincia = provinciaServicio.obtenerProvinciaPorId(id);
        List<Pais> listaPaises = paisServicio.obtenerTodosPaises();
        modelo.addAttribute("provincia", provincia);
        modelo.addAttribute("listaPaises", listaPaises);
        return "editar_provincia";
    }

    @PostMapping("/editarProvincia/{id}")
    public String editarProvincia(@PathVariable("id") Long id, @ModelAttribute("provincia") Provincia provincia) {
        Provincia provinciaExistente = provinciaServicio.obtenerProvinciaPorId(id);
        provinciaExistente.setNombre(provincia.getNombre());
        provinciaExistente.setPais(provincia.getPais());

        provinciaServicio.guardarProvincia(provinciaExistente);
        return "redirect:/provincias";
    }


    @GetMapping("/eliminarProvincia/{id}")
    public String eliminarProvincia(@PathVariable("id") Long id, Model modelo) {
        if(provinciaServicio.existenCiudadesEnProvincia(id)){
            modelo.addAttribute("error", "No se puede eliminar la provincia, hay ciudades asociadas a la misma.");
            List<Provincia> listaProvincias = provinciaServicio.obtenerTodasProvincias();
            modelo.addAttribute("listaProvincias", listaProvincias);
            return "provincias";
        }
        provinciaServicio.eliminarProvincia(id);
        return "redirect:/provincias";
    }

}
