package com.example.RestauranteAppV1.Controladores;

import com.example.RestauranteAppV1.Entidades.*;
import com.example.RestauranteAppV1.Servicio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class PerfilControlador {
    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/perfil")
    public String mostrarPerfil(Model modelo, Principal principal) {
        String username = principal.getName();
        Usuario usuario = usuarioServicio.obtenerUsuarioPorUsername(username);
        modelo.addAttribute("usuario", usuario);
        return "perfil";
    }

    @Autowired
    private PedidoServicio pedidoServicio;

    @GetMapping("/perfil/pedidos")
    public String mostrarPedidosDeUsuario(Model modelo, Principal principal) {
        String username = principal.getName();
        Usuario usuario = usuarioServicio.obtenerUsuarioPorUsername(username);

        List<Pedido> pedidos = pedidoServicio.obtenerPedidosPorCreador(usuario);
        modelo.addAttribute("pedidos", pedidos);

        return "mis_pedidos";
    }


    @Autowired
    private PaisServicio paisServicio;
    @Autowired
    private ProvinciaServicio provinciaServicio;
    @Autowired
    private CiudadServicio ciudadServicio;

    @GetMapping("/perfil/editar")
    public String mostrarFormularioEditar(Model modelo, Principal principal) {
        String username = principal.getName();
        Usuario usuario = usuarioServicio.obtenerUsuarioPorUsername(username);
        modelo.addAttribute("usuario", usuario);

        List<Pais> listaPaises = paisServicio.obtenerTodosPaises();
        List<Provincia> listaProvincias = provinciaServicio.obtenerTodasProvincias();
        List<Ciudad> listaCiudades = ciudadServicio.obtenerTodasCiudades();

        modelo.addAttribute("listaPaises", listaPaises);
        modelo.addAttribute("listaProvincias", listaProvincias);
        modelo.addAttribute("listaCiudades", listaCiudades);

        return "editar_perfil";
    }

    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("/perfil/editar")
    public String editarPerfil(@ModelAttribute("usuario") Usuario usuario, @RequestParam(value = "confirmarClave", required = false)String confirmarClave) {
        Usuario usuarioExistente = usuarioServicio.obtenerUsuarioPorId(usuario.getUsuarioId());
        usuarioExistente.setNombre(usuario.getNombre());
        usuarioExistente.setApellido(usuario.getApellido());
        //usuarioExistente.setUsername(usuario.getUsername());

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


        return "redirect:/perfil";
    }
}
