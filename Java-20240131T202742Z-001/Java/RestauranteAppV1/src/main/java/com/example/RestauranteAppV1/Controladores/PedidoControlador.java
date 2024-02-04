package com.example.RestauranteAppV1.Controladores;

import com.example.RestauranteAppV1.Entidades.*;
import com.example.RestauranteAppV1.Servicio.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@Controller
public class PedidoControlador {
    @Autowired
    private PedidoServicio pedidoServicio;

    @GetMapping("/pedidos")
    public String verListaPedidos(Model modelo) {
        List<Pedido> listaPedidos = pedidoServicio.obtenerPedidos();
        modelo.addAttribute("listaPedidos", listaPedidos);
        return "pedidos";
    }

    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private MesaServicio mesaServicio;
/*
    @GetMapping("/pedidos/nuevoPedidoCliente")
    public String mostrarFormularioDeRegistrarPedidoCliente(Model modelo) {
        List<Usuario> listaUsuarios = usuarioServicio.obtenerTodosUsuarios();
        List<Mesa> listaMesas = mesaServicio.obtenerMesasDisponibles();
        modelo.addAttribute("listaUsuarios", listaUsuarios);
        modelo.addAttribute("listaMesas", listaMesas);
        modelo.addAttribute("pedido", new Pedido());


        return "nuevo_pedido_cliente";
    }

 */
    @GetMapping("/pedidos/nuevoPedido")
    public String mostrarFormularioDeRegistrarPedido(Model modelo, Principal principal) {
        List<Usuario> listaUsuarios = usuarioServicio.obtenerTodosUsuarios();
        List<Mesa> listaMesas = mesaServicio.obtenerMesasDisponibles();
        modelo.addAttribute("listaUsuarios", listaUsuarios);
        modelo.addAttribute("listaMesas", listaMesas);


        // Obtener el nombre de usuario del usuario autenticado
        String username = principal.getName();

        // Obtener el Usuario desde el servicio basado en el nombre de usuario
        Usuario usuario = usuarioServicio.obtenerUsuarioPorUsername(username);

        // Verificar el rol del usuario y decidir qué vista mostrar
        if (usuario.getRol().getNombre().equals("CLIENTE")) {
            modelo.addAttribute("pedido", new Pedido());
            return "nuevo_pedido_cliente";
        } else {
            modelo.addAttribute("pedido", new Pedido());
            return "nuevo_pedido";
        }


    }

    @Autowired
    DetallePedidoServicio detallePedidoServicio;
    @GetMapping("/pedidos/detalles/{id}")
    public String verDetallesPedido(@PathVariable("id") Long id, Model modelo) {
        Pedido pedido = pedidoServicio.obtenerPedidoPorId(id);
        List<DetallePedido> detalles = detallePedidoServicio.obtenerDetallesPorPedido(id);
        modelo.addAttribute("pedido", pedido);
        modelo.addAttribute("detalles", detalles);
        return "detalle_pedido_particular";
    }

    @PostMapping("/pedidos/guardarPedido")
    public String guardarPedido(@ModelAttribute("pedido") Pedido pedido, Principal principal) {
        Date fechaActual = new Date();

        // Obtener el nombre de usuario del usuario autenticado
        String username = principal.getName();

        // Obtener el Usuario desde el servicio basado en el nombre de usuario
        Usuario creadorPedido = usuarioServicio.obtenerUsuarioPorUsername(username);

        // Verificar si el usuario tiene el rol "CLIENTE"
        if (creadorPedido.getRol().getNombre().equals("CLIENTE")) {
            // Asignar el ID del usuario cliente logueado al pedido

            pedido.setUsuario(creadorPedido);
            pedido.setEstado("PENDIENTE");
        }


        // Asignar el Usuario logueado como el creador del Pedido

        pedido.setCreadorPedido(creadorPedido);

        // Poner el total en 0
        pedido.setTotal(0.0);

        // Asignar fecha actual a pedido
        pedido.setFecha(fechaActual);


        Mesa mesaAsociada = pedido.getMesa();
        if (pedido.getEstado().equals("PAGADO")) {
            mesaAsociada.setEstado("DISPONIBLE");
        } else {
            mesaAsociada.setEstado("OCUPADA");
        }

        mesaServicio.guardarMesa(mesaAsociada);


        pedidoServicio.guardarPedido(pedido);
        // Paso 1: Obtener el ID del pedido recién creado
        Long nuevoPedidoId = pedido.getPedidoId();

        // Paso 2: Construir la URL para los detalles del pedido
        String urlDetallesPedido = "/pedidos/detalles/" + nuevoPedidoId;

        // Paso 3: Redirigir a la URL de los detalles del pedido
        return "redirect:" + urlDetallesPedido;

    }



    @GetMapping("/pedidos/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long id, Model modelo) {
        Pedido pedido = pedidoServicio.obtenerPedidoPorId(id);
        List<Usuario> listaUsuarios = usuarioServicio.obtenerTodosUsuarios();
        List<Mesa> listaMesas = mesaServicio.obtenerTodasMesas();
        modelo.addAttribute("listaUsuarios", listaUsuarios);
        modelo.addAttribute("listaMesas", listaMesas);
        modelo.addAttribute("pedido", pedido);

        return "editar_pedido";
    }

    @PostMapping("/pedidos/editar/{id}")
    public String editarPedido(@PathVariable("id") Long id, @ModelAttribute("pedido") Pedido pedido) {
        Pedido pedidoExistente = pedidoServicio.obtenerPedidoPorId(id);

        pedidoExistente.setUsuario(pedido.getUsuario());
        pedidoExistente.setMesa(pedido.getMesa());
        pedidoExistente.setFecha(pedido.getFecha());
        pedidoExistente.setEstado(pedido.getEstado());


        pedidoServicio.guardarPedido(pedidoExistente);


        // Aquí asignamos el estado de la mesa según el estado del pedido
        Mesa mesaAsociada = pedido.getMesa();
        if (pedido.getEstado().equals("PAGADO")) {
            mesaAsociada.setEstado("DISPONIBLE");
        } else {
            mesaAsociada.setEstado("OCUPADA");
        }
        mesaServicio.guardarMesa(mesaAsociada);



        return "redirect:/pedidos";
    }
/*
    @GetMapping("/pedidos/eliminar/{id}")
    public String eliminarPedido(@PathVariable("id") Long id) {
        Pedido pedido = pedidoServicio.obtenerPedidoPorId(id);
        Mesa mesaAsociada = pedido.getMesa();
        mesaAsociada.setEstado("DISPONIBLE");
        mesaServicio.guardarMesa(mesaAsociada);

        pedidoServicio.eliminarPedido(id);

        return "redirect:/pedidos";
    }
*/
    @GetMapping("/pedidos/eliminar/{id}")
        public String eliminarPedido(@PathVariable("id") Long id, @RequestHeader(value = "Referer", required = false) String referer) {
        Pedido pedido = pedidoServicio.obtenerPedidoPorId(id);
        Mesa mesaAsociada = pedido.getMesa();
        mesaAsociada.setEstado("DISPONIBLE");
        mesaServicio.guardarMesa(mesaAsociada);

        pedidoServicio.eliminarPedido(id);

        // Obtener la URL de la página anterior desde la cabecera "Referer"
        // y verificar si es la página "mis_pedidos"
        if (referer != null && referer.contains("/perfil/pedidos")) {
            // Redirigir a "mis_pedidos"
            return "redirect:/perfil/pedidos";
        }

        // En otros casos, redirigir a "/pedidos"
        return "redirect:/pedidos";
    }


    @GetMapping("/pedidos/todosIds")
    @ResponseBody
    public List<Long> obtenerTodosIdsPedidos() {
        return pedidoServicio.obtenerTodosIdsPedidos();
    }



}
