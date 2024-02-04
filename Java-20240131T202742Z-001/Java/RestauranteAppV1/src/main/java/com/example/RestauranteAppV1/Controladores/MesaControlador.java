package com.example.RestauranteAppV1.Controladores;

import com.example.RestauranteAppV1.Entidades.Mesa;
import com.example.RestauranteAppV1.Servicio.MesaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MesaControlador {

    @Autowired
    private MesaServicio mesaServicio;

    /*
    @GetMapping("/mesas")
    public String verListaMesas(Model modelo) {
        List<Mesa> listaMesas = mesaServicio.obtenerTodasMesas();
        modelo.addAttribute("listaMesas", listaMesas);
        modelo.addAttribute("error", modelo.getAttribute("error"));
        return "mesas";
    }
    */
    @GetMapping("/mesas")
    public String verListaMesas(Model modelo, @RequestParam(required = false) String error) {
        List<Mesa> listaMesas = mesaServicio.obtenerTodasMesas();
        modelo.addAttribute("listaMesas", listaMesas);
        modelo.addAttribute("error", error);
        return "mesas";
    }


    @GetMapping("/mesas/nuevaMesa")
    public String mostrarFormularioDeRegistrarMesa(Model modelo) {
        modelo.addAttribute("mesa", new Mesa());
        return "nueva_mesa";
    }

    @PostMapping("/mesas/guardarMesa")
    public String guardarMesa(@ModelAttribute("mesa") Mesa mesa) {
        mesaServicio.guardarMesa(mesa);
        return "redirect:/mesas";
    }

    @GetMapping("/mesas/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long id, Model modelo) {
        Mesa mesa = mesaServicio.obtenerMesaPorId(id);
        modelo.addAttribute("mesa", mesa);
        return "editar_mesa";
    }

    @PostMapping("/mesas/editar/{id}")
    public String editarMesa(@PathVariable("id") Long id, @ModelAttribute("mesa") Mesa mesa) {
        Mesa mesaExistente = mesaServicio.obtenerMesaPorId(id);
        mesaExistente.setNumero(mesa.getNumero());
        mesaExistente.setCapacidad(mesa.getCapacidad());
        mesaExistente.setEstado(mesa.getEstado());
        // Actualizar otros campos si es necesario

        mesaServicio.guardarMesa(mesaExistente);
        return "redirect:/mesas";
    }

    @GetMapping("/mesas/eliminar/{id}")
    public String eliminarMesa(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            mesaServicio.eliminarMesa(id);
        } catch (Exception e) {
            // Capturar cualquier excepción y enviar un mensaje de error
            redirectAttributes.addAttribute("error", "No se puede eliminar la mesa, ya que esta asociada a un pedido.");
        }
        return "redirect:/mesas";
    }



    @GetMapping("/mesas/todasMesas")
    @ResponseBody
    public ResponseEntity<List<Mesa>> getMesas() {
        List<Mesa> mesas = mesaServicio.obtenerTodasMesas();
        return ResponseEntity.ok(mesas);
    }
}
