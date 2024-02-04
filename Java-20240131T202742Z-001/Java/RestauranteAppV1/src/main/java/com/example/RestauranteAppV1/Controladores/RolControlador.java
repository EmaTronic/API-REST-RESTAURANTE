package com.example.RestauranteAppV1.Controladores;

import com.example.RestauranteAppV1.Entidades.Rol;
import com.example.RestauranteAppV1.Servicio.RolServicio;
import com.example.RestauranteAppV1.Servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RolControlador {

    @Autowired
    private RolServicio rolServicio;

    @GetMapping("/roles")
    public String verListaRoles(Model modelo) {
        List<Rol> listaRoles = rolServicio.obtenerTodosRoles();
        modelo.addAttribute("listaRoles", listaRoles);
        return "roles";
    }

    @GetMapping("/roles/nuevoRol")
    public String mostrarFormularioDeRegistrarRol(Model modelo) {
        modelo.addAttribute("rol", new Rol());
        return "nuevo_rol";
    }

    @PostMapping("/roles/guardarRol")
    public String guardarRol(@ModelAttribute("rol") Rol rol) {
        rolServicio.guardarRol(rol);
        return "redirect:/roles";
    }

    @GetMapping("/roles/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long id, Model modelo) {
        Rol rol = rolServicio.obtenerRolPorId(id);
        modelo.addAttribute("rol", rol);
        return "editar_rol";
    }

    @PostMapping("/roles/editar/{id}")
    public String editarRol(@PathVariable("id") Long id, @ModelAttribute("rol") Rol rol) {
        Rol rolExistente = rolServicio.obtenerRolPorId(id);
        rolExistente.setNombre(rol.getNombre());
        // Actualizar otros campos si es necesario

        rolServicio.guardarRol(rolExistente);
        return "redirect:/roles";
    }



    @GetMapping("/roles/eliminar/{id}")
    public String eliminarRol(@PathVariable("id") Long id, Model modelo) {
        if (rolServicio.existenUsuariosConRol(id)) {
            modelo.addAttribute("error", "No se puede eliminar el rol, hay usuarios asignados a este rol.");
            List<Rol> listaRoles = rolServicio.obtenerTodosRoles();
            modelo.addAttribute("listaRoles", listaRoles);
            return "roles";
        }

        rolServicio.eliminarRol(id);
        return "redirect:/roles";
    }

}

