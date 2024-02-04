package com.example.RestauranteAppV1.Repositorio;

import com.example.RestauranteAppV1.Entidades.Mesa;
import com.example.RestauranteAppV1.Entidades.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ReservaRepositorio extends JpaRepository<Reserva, Long> {
    @Query("SELECT r.rangoHorario FROM Reserva r WHERE r.mesa.id = :mesaId AND r.fecha = :fecha")
    List<String> obtenerHorariosOcupadosPorMesaYFecha(@Param("mesaId") Long mesaId, @Param("fecha") LocalDate fecha);

    // Método para encontrar una reserva por Fecha
    List<Reserva> findByFecha(LocalDate fecha);

    // Método para encontrar una reserva por DNI
    List<Reserva> findByDni(String dni);

    // Método para encontrar una reserva por código
    List<Reserva> findByCodigo(String codigo);
}
