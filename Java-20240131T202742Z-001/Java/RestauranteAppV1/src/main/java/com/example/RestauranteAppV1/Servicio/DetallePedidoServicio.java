package com.example.RestauranteAppV1.Servicio;

import com.example.RestauranteAppV1.Entidades.DetallePedido;
import com.example.RestauranteAppV1.Repositorio.DetallePedidoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DetallePedidoServicio {
    @Autowired
    private DetallePedidoRepositorio detallePedidoRepositorio;
    public List<DetallePedido> obtenerDetallesPorPedido(Long pedidoId) {
        return detallePedidoRepositorio.findByPedido_PedidoId(pedidoId);
    }

    public List<DetallePedido> obtenerTodosDetalles() {
        return detallePedidoRepositorio.findAll();
    }

    public Optional<DetallePedido> obtenerDetallePorId(Long id) {
        return detallePedidoRepositorio.findById(id);
    }

    public void guardarDetallePedido(DetallePedido detalle) {
        detallePedidoRepositorio.save(detalle);
    }

    public void eliminarDetallePedido(Long id) {
        detallePedidoRepositorio.deleteById(id);
    }
}
