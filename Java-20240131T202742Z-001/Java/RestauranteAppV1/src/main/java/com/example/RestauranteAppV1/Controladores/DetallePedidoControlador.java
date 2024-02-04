package com.example.RestauranteAppV1.Controladores;

import com.example.RestauranteAppV1.Entidades.Categoria;
import com.example.RestauranteAppV1.Entidades.DetallePedido;
import com.example.RestauranteAppV1.Entidades.Pedido;
import com.example.RestauranteAppV1.Entidades.Producto;
import com.example.RestauranteAppV1.Servicio.CategoriaServicio;
import com.example.RestauranteAppV1.Servicio.DetallePedidoServicio;
import com.example.RestauranteAppV1.Servicio.PedidoServicio;
import com.example.RestauranteAppV1.Servicio.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
public class DetallePedidoControlador {
    @Autowired
    private DetallePedidoServicio detallePedidoServicio;

    @Autowired
    private PedidoServicio pedidoServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @GetMapping("/detallePedidos")
    public String verListaDetalles(Model modelo) {
        List<DetallePedido> listaDetalles = detallePedidoServicio.obtenerTodosDetalles();
        modelo.addAttribute("listaDetalles", listaDetalles);
        return "detallePedidos";
    }

    @Autowired
    private CategoriaServicio categoriaServicio;
    @GetMapping("/detallePedidos/nuevoDetallePedido/{pedidoId}")
    public String mostrarFormularioDeRegistrarDetalle(@PathVariable("pedidoId") Long pedidoId, Model modelo) {
        List<Producto> listaProductos = productoServicio.obtenerProductosDisponibles();
        List<Categoria> listaCategorias = categoriaServicio.obtenerTodasCategorias();
        modelo.addAttribute("pedidoId", pedidoId);
        modelo.addAttribute("listaProductos", listaProductos);
        modelo.addAttribute("listaCategorias", listaCategorias);
        modelo.addAttribute("detallePedido", new DetallePedido());

        return "nuevo_detallePedido";
    }


    /*
    @GetMapping("/detallePedidos/nuevoDetallePedido")
    public String mostrarFormularioDeRegistrarDetalle(Model modelo) {
        List<Pedido> listaPedidos = pedidoServicio.obtenerPedidos();
        List<Producto> listaProductos = productoServicio.obtenerTodosProductos();
        modelo.addAttribute("listaPedidos", listaPedidos);
        modelo.addAttribute("listaProductos", listaProductos);
        modelo.addAttribute("detallePedido", new DetallePedido());

        return "nuevo_detallePedido";
    }
*/
    @PostMapping("/detallePedidos/guardarDetallePedido")
    public String guardarDetallePedido(@ModelAttribute("detallePedido") DetallePedido detallePedido, RedirectAttributes redirectAttributes) {
        Producto producto = detallePedido.getProducto();
        int cantidad = detallePedido.getCantidad();
        int stockActual = producto.getStock();

        // Verificar si el stock es suficiente para la cantidad solicitada
        if (stockActual >= cantidad) {
            // Actualizar el stock
            int nuevoStock = stockActual - cantidad;
            producto.setStock(nuevoStock);

            // Si el stock resultante es cero, marcar el producto como no disponible
            if (nuevoStock == 0) {
                producto.setDisponible(0);
            }

            // Guardar el producto actualizado
            productoServicio.guardarProducto(producto);

            // Guardar el detalle
            detallePedidoServicio.guardarDetallePedido(detallePedido);

            // Recalcular y guardar el total del pedido
            Pedido pedido = detallePedido.getPedido();
            pedido.recalcularTotal();
            pedidoServicio.guardarPedido(pedido);

            return "redirect:/pedidos/detalles/" + detallePedido.getPedido().getPedidoId();
        } else {
            // Si hay un error, agrega el mensaje como atributo flash
            redirectAttributes.addFlashAttribute("errorStock", "La cantidad solicitada supera el stock disponible.");
            // Puedes redirigir de vuelta al formulario con un mensaje de error
            return "redirect:/detallePedidos/nuevoDetallePedido/" + detallePedido.getPedido().getPedidoId();
        }
    }

    @GetMapping("/detallePedidos/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long id, Model modelo) {
        Optional<DetallePedido> detallePedido = detallePedidoServicio.obtenerDetallePorId(id);
        if (detallePedido.isPresent()) {
            List<Pedido> listaPedidos = pedidoServicio.obtenerPedidos();
            List<Producto> listaProductos = productoServicio.obtenerProductosDisponibles();
            modelo.addAttribute("listaPedidos", listaPedidos);
            modelo.addAttribute("listaProductos", listaProductos);
            modelo.addAttribute("detallePedido", detallePedido.get());

            List<Categoria> listaCategorias = categoriaServicio.obtenerTodasCategorias();
            modelo.addAttribute("listaCategorias", listaCategorias);

            return "editar_detallePedido";
        } else {
            return "redirect:/detallePedidos";
        }
    }

    @PostMapping("/detallePedidos/editar/{id}")
    public String editarDetallePedido(@PathVariable("id") Long id, @ModelAttribute("detallePedido") DetallePedido detallePedido) {
        Optional<DetallePedido> detalleExistente = detallePedidoServicio.obtenerDetallePorId(id);
        if (detalleExistente.isPresent()) {
            DetallePedido detalle = detalleExistente.get();

            detalle.setPedido(detallePedido.getPedido());
            detalle.setProducto(detallePedido.getProducto());
            detalle.setCantidad(detallePedido.getCantidad());
            detalle.setPrecioUnitario(detallePedido.getPrecioUnitario());
            detalle.setSubtotal(detallePedido.getSubtotal());

            Pedido pedido = detallePedido.getPedido();
            pedido.recalcularTotal();
            pedidoServicio.guardarPedido(pedido);


            detallePedidoServicio.guardarDetallePedido(detalle);
        }
        return "redirect:/pedidos/detalles/" + detallePedido.getPedido().getPedidoId();

    }

    @GetMapping("/detallePedidos/eliminar/{id}")
    public String eliminarDetallePedido(@PathVariable("id") Long id) {
        Optional<DetallePedido> detallePedidoOptional = detallePedidoServicio.obtenerDetallePorId(id);

        if (detallePedidoOptional.isPresent()) {
            DetallePedido detallePedido = detallePedidoOptional.get();
            Long pedidoId = detallePedido.getPedido().getPedidoId(); // Obtienes el ID del pedido asociado al detalle

            // Obtener la cantidad del detalle que estás eliminando
            int cantidadEliminada = detallePedido.getCantidad();

            detallePedidoServicio.eliminarDetallePedido(id);


            Pedido pedido = detallePedido.getPedido();
            pedido.recalcularTotal();
            pedidoServicio.guardarPedido(pedido);


            // Obtener el producto asociado al detalle
            Producto producto = detallePedido.getProducto();

            // Agregar la cantidad eliminada al stock del producto
            producto.setStock(producto.getStock() + cantidadEliminada);

            // Si el stock resultante es mayor a cero, marcar el producto como disponible
            if (producto.getStock() > 0) {
                producto.setDisponible(1);
            }

            // Guardar el producto actualizado
            productoServicio.guardarProducto(producto);


            return "redirect:/pedidos/detalles/" + pedidoId;
        } else {
            // Manejar el caso donde el DetallePedido no existe
            return "redirect:/pedidos"; // O redireccionar a la página adecuada
        }
    }
}
