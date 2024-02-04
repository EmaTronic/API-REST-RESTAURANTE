package com.example.RestauranteAppV1.Servicio;

import com.example.RestauranteAppV1.Entidades.Mesa;
import com.example.RestauranteAppV1.Entidades.Reserva;
import com.example.RestauranteAppV1.Repositorio.MesaRepositorio;
import com.example.RestauranteAppV1.Repositorio.ReservaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Component
public class ReservaServicio {

    @Autowired
    private ReservaRepositorio reservaRepositorio;

    @Autowired
    public ReservaServicio(ReservaRepositorio reservaRepositorio) {
        this.reservaRepositorio = reservaRepositorio;
    }


    public List<Reserva> obtenerTodasLasReservas() {
        return reservaRepositorio.findAll();
    }

    public Optional<Reserva> obtenerReservaPorId(Long id) {
        return reservaRepositorio.findById(id);
    }

    public Reserva guardarReserva(Reserva reserva) {
        // Puedes agregar validaciones antes de guardar la reserva si es necesario
        return reservaRepositorio.save(reserva);
    }

    public void eliminarReserva(Long id) {
        reservaRepositorio.deleteById(id);
    }


    public List<String> obtenerHorariosOcupadosPorMesaYFecha(Long mesaId, LocalDate fecha) {
        List<String> horariosOcupados = reservaRepositorio.obtenerHorariosOcupadosPorMesaYFecha(mesaId, fecha);
        return horariosOcupados;
    }



    @Autowired
    private MesaRepositorio mesaRepositorio;

    @Scheduled(fixedRate = 60000) // Se ejecuta cada minuto (ajusta el intervalo según tus necesidades)
    public void verificarReservasHoy() {
        // Obtener la fecha actual
        LocalDate fechaHoy = LocalDate.now();

        // Obtener la hora actual
        LocalTime horaActual = LocalTime.now();

        // Calcular la hora límite (1 hora y 30 minutos después de la hora actual)
        LocalTime horaLimite = horaActual.plusHours(1).plusMinutes(30);

        // Obtener todas las reservas para hoy
        List<Reserva> reservasHoy = reservaRepositorio.findByFecha(fechaHoy);

        // Filtrar las mesas asociadas a reservas dentro de las próximas 1:30
        List<Mesa> mesasReservadas = reservasHoy.stream()
                .filter(reserva ->
                        reserva.getHoraInicioAsLocalTime().isAfter(horaActual) &&
                                reserva.getHoraInicioAsLocalTime().isBefore(horaLimite))
                .map(Reserva::getMesa)
                .collect(Collectors.toList());

        // Marcar como "RESERVADA" las mesas asociadas a las reservas dentro del período
        mesasReservadas.forEach(mesa -> {
            if (!mesa.getEstado().equals("RESERVADA") && mesa.getEstado().equals("DISPONIBLE")) {
                mesa.setEstado("RESERVADA");
                mesaRepositorio.save(mesa);
            }
        });

    }

    @Scheduled(cron = "0 0 0 * * *") // Se ejecuta todos los días a la medianoche
    public void liberarMesasAlMedianoche() {
        // Obtener todas las mesas reservadas
        List<Mesa> mesasReservadas = mesaRepositorio.findByEstado("RESERVADA");

        // Marcar como "DISPONIBLE" todas las mesas reservadas
        mesasReservadas.forEach(mesa -> {
            mesa.setEstado("DISPONIBLE");
            mesaRepositorio.save(mesa);
        });
    }


    public List<Reserva> obtenerReservaPorDni(String dni) {
        return reservaRepositorio.findByDni(dni);
    }

    public List<Reserva> obtenerReservaPorCodigo(String codigo) {
        return reservaRepositorio.findByCodigo(codigo);
    }



}
