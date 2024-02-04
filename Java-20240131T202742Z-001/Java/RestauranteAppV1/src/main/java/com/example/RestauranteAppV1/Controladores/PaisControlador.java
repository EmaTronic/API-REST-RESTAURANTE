package com.example.RestauranteAppV1.Controladores;

import com.example.RestauranteAppV1.Entidades.Pais;
import com.example.RestauranteAppV1.Servicio.PaisServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/paises")
public class PaisControlador {

    @Autowired
    private PaisServicio paisServicio;

    @GetMapping
    public String verListaPaises(Model modelo) {
        List<Pais> listaPaises = paisServicio.obtenerTodosPaises();
        modelo.addAttribute("listaPaises", listaPaises);
        return "paises";
    }

    @GetMapping("/nuevoPais")
    public String mostrarFormularioDeRegistrarPais(Model modelo) {
        modelo.addAttribute("pais", new Pais());
        return "nuevo_pais";
    }

    @PostMapping("/guardarPais")
    public String guardarPais(@ModelAttribute("pais") Pais pais) {
        paisServicio.guardarPais(pais);
        return "redirect:/paises";
    }

    @GetMapping("/editarPais/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long id, Model modelo) {
        Pais pais = paisServicio.obtenerPaisPorId(id);
        modelo.addAttribute("pais", pais);
        return "editar_pais";
    }

    @PostMapping("/editarPais/{id}")
    public String editarPais(@PathVariable("id") Long id, @ModelAttribute("pais") Pais pais) {
        Pais paisExistente = paisServicio.obtenerPaisPorId(id);
        paisExistente.setNombre(pais.getNombre());

        paisServicio.guardarPais(paisExistente);
        return "redirect:/paises";
    }


    @GetMapping("/eliminarPais/{id}")
    public String eliminarPais(@PathVariable("id") Long id, Model modelo) {
        if(paisServicio.existenProvinciasEnPais(id)){
            modelo.addAttribute("error", "No se puede eliminar el pais, hay provincias asociadas al mismo.");
            List<Pais> listaPaises = paisServicio.obtenerTodosPaises();
            modelo.addAttribute("listaPaises", listaPaises);
            return "paises";
        }
        paisServicio.eliminarPais(id);
        return "redirect:/paises";
    }

}
