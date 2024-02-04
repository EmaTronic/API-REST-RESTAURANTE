package com.example.RestauranteAppV1.Controladores;


import com.example.RestauranteAppV1.Entidades.*;
import com.example.RestauranteAppV1.Repositorio.RolRepositorio;
import com.example.RestauranteAppV1.Servicio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
@Controller
@RequestMapping("/usuarios")

public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping
    public String verListaUsuarios(Model modelo, @RequestParam(required = false) String error) {
        List<Usuario> listaUsuarios = usuarioServicio.obtenerTodosUsuarios();
        modelo.addAttribute("listaUsuarios", listaUsuarios);
        modelo.addAttribute("error", error);
        return "usuarios";
    }

    @Autowired
    private RolServicio rolServicio;
    @Autowired
    private PaisServicio paisServicio;
    @Autowired
    private ProvinciaServicio provinciaServicio;
    @Autowired
    private CiudadServicio ciudadServicio;

    @Autowired
    private RolRepositorio rolRepositorio;


    @GetMapping("/nuevoUsuario")
    public String mostrarFormularioDeRegistrarUsuario(Model modelo) {
        modelo.addAttribute("usuario", new Usuario());

        List<Rol> listaRoles = rolServicio.obtenerTodosRoles();
        List<Pais> listaPaises = paisServicio.obtenerTodosPaises();
        List<Provincia> listaProvincias = provinciaServicio.obtenerTodasProvincias();
        List<Ciudad> listaCiudades = ciudadServicio.obtenerTodasCiudades();

        modelo.addAttribute("listaRoles", listaRoles);
        modelo.addAttribute("listaPaises", listaPaises);
        modelo.addAttribute("listaProvincias", listaProvincias);
        modelo.addAttribute("listaCiudades", listaCiudades);
        return "nuevo_usuario";
    }


    @GetMapping("/nuevoCliente")
    public String mostrarFormularioDeRegistrarCliente(Model modelo) {

        modelo.addAttribute("usuario", new Usuario());

        List<Rol> listaRoles = rolServicio.obtenerTodosRoles();
        List<Pais> listaPaises = paisServicio.obtenerTodosPaises();
        List<Provincia> listaProvincias = provinciaServicio.obtenerTodasProvincias();
        List<Ciudad> listaCiudades = ciudadServicio.obtenerTodasCiudades();

        modelo.addAttribute("listaRoles", listaRoles);
        modelo.addAttribute("listaPaises", listaPaises);
        modelo.addAttribute("listaProvincias", listaProvincias);
        modelo.addAttribute("listaCiudades", listaCiudades);

        return "nuevo_cliente";
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*
    @PostMapping("/guardarUsuario")
    public String guardarUsuario(@ModelAttribute("usuario") Usuario usuario) {
        usuario.setClave(passwordEncoder.encode(usuario.getClave()));
        usuarioServicio.guardarUsuario(usuario);
        return "redirect:/usuarios";
    }
    */
    @PostMapping("/guardarUsuario")
    public String guardarUsuario(@ModelAttribute("usuario") Usuario usuario,
                                 @RequestParam("confirmarClave") String confirmarClave,
                                 Model modelo) {
        // Verificar que las contraseñas coincidan
        if (!usuario.getClave().equals(confirmarClave)) {
            modelo.addAttribute("error", "Las contraseñas no coinciden. Por favor, inténtelo de nuevo.");
            // Vuelve a cargar el formulario de registro con el mensaje de error
            return mostrarFormularioDeRegistrarCliente(modelo);
        }

        usuario.setClave(passwordEncoder.encode(usuario.getClave()));
        usuarioServicio.guardarUsuario(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/editarUsuario/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long id, Model modelo) {
        Usuario usuario = usuarioServicio.obtenerUsuarioPorId(id);
        modelo.addAttribute("usuario", usuario);

        List<Rol> listaRoles = rolServicio.obtenerTodosRoles();
        List<Pais> listaPaises = paisServicio.obtenerTodosPaises();
        List<Provincia> listaProvincias = provinciaServicio.obtenerTodasProvincias();
        List<Ciudad> listaCiudades = ciudadServicio.obtenerTodasCiudades();

        modelo.addAttribute("listaRoles", listaRoles);
        modelo.addAttribute("listaPaises", listaPaises);
        modelo.addAttribute("listaProvincias", listaProvincias);
        modelo.addAttribute("listaCiudades", listaCiudades);

        return "editar_usuario";
    }

    @PostMapping("/editarUsuario/{id}")
    public String editarUsuario(@PathVariable("id") Long id, @ModelAttribute("usuario") Usuario usuario, @RequestParam(value = "confirmarClave", required = false)String confirmarClave) {
        Usuario usuarioExistente = usuarioServicio.obtenerUsuarioPorId(id);
        usuarioExistente.setNombre(usuario.getNombre());
        usuarioExistente.setApellido(usuario.getApellido());
        usuarioExistente.setUsername(usuario.getUsername());

        usuarioExistente.setRol(usuario.getRol());
        usuarioExistente.setTelefono(usuario.getTelefono());
        usuarioExistente.setPais(usuario.getPais());
        usuarioExistente.setProvincia(usuario.getProvincia());
        usuarioExistente.setCiudad(usuario.getCiudad());

        // Verificar si se proporcionó una nueva contraseña
        String nuevaClave = usuario.getClave();

        if (nuevaClave != null && !nuevaClave.isEmpty()) {
            // Verificar que las contraseñas coincidan
            if (nuevaClave.equals(confirmarClave)) {
                usuarioExistente.setClave(passwordEncoder.encode(nuevaClave)); // Encriptar la nueva contraseña
            }
        }



        usuarioServicio.guardarUsuario(usuarioExistente);
        return "redirect:/usuarios";
    }
/*
    @GetMapping("/eliminarUsuario/{id}")
    public String eliminarUsuario(@PathVariable("id") Long id) {
        usuarioServicio.eliminarUsuario(id);
        return "redirect:/usuarios";
    }

 */
@GetMapping("/eliminarUsuario/{id}")
public String eliminarUsuario(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
    try {
        usuarioServicio.eliminarUsuario(id);
    } catch (Exception e) {
        // Capturar cualquier excepción y enviar un mensaje de error
        redirectAttributes.addAttribute("error", "No se puede eliminar el usuario, ya que hay pedidos relacionados al mismo.");
    }
    return "redirect:/usuarios";
}
}



