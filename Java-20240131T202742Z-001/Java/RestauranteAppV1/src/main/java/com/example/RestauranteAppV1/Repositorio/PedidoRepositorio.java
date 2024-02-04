package com.example.RestauranteAppV1.Repositorio;

import com.example.RestauranteAppV1.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.RestauranteAppV1.Entidades.Pedido;

import java.util.List;

@Repository
public interface PedidoRepositorio extends JpaRepository<Pedido, Long> {
    List<Pedido> findByCreadorPedido(Usuario creador);
    // Recupera todos los IDs de pedido
    @Query("SELECT p.pedidoId FROM Pedido p ORDER BY p.pedidoId ASC")
    List<Long> findAllIds();
}
