package com.example.RestauranteAppV1.Servicio;

import com.example.RestauranteAppV1.Entidades.DetallePedido;
import com.example.RestauranteAppV1.Entidades.Pedido;
import com.example.RestauranteAppV1.Entidades.Usuario;
import com.example.RestauranteAppV1.Repositorio.PedidoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PedidoServicio {
    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    public List<Pedido> obtenerPedidosPorCreador(Usuario creador) {
        return pedidoRepositorio.findByCreadorPedido(creador);
    }


    public List<Pedido> obtenerPedidos() {
        return pedidoRepositorio.findAll();
    }

        public void guardarPedido(Pedido pedido) {
        pedidoRepositorio.save(pedido);
    }

    public Pedido obtenerPedidoPorId(Long id) {
        return pedidoRepositorio.findById(id).orElse(null);
    }

    public void eliminarPedido(Long id) {
        pedidoRepositorio.deleteById(id);
    }


    public List<Long> obtenerTodosIdsPedidos() {
        return pedidoRepositorio.findAllIds();
    }
}
