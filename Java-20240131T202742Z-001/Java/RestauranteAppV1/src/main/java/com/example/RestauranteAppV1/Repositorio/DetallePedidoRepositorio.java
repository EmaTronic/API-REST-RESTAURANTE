package com.example.RestauranteAppV1.Repositorio;

import com.example.RestauranteAppV1.Entidades.DetallePedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetallePedidoRepositorio extends JpaRepository<DetallePedido, Long> {
    List<DetallePedido> findByPedido_PedidoId(Long pedidoId);
}
