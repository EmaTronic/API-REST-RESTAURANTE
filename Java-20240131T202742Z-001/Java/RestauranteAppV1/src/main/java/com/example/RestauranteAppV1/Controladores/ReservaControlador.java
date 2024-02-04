package com.example.RestauranteAppV1.Controladores;

import com.example.RestauranteAppV1.Entidades.Mesa;
import com.example.RestauranteAppV1.Entidades.Reserva;
import com.example.RestauranteAppV1.Servicio.MesaServicio;
import com.example.RestauranteAppV1.Servicio.ReservaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/reservas")
public class ReservaControlador {

    @Autowired
    private ReservaServicio reservaServicio;

    @Autowired
    private MesaServicio mesaServicio;

    @GetMapping
    public String verListaDeReservas(Model modelo) {
        List<Reserva> listaReservas = reservaServicio.obtenerTodasLasReservas();
        modelo.addAttribute("listaReservas", listaReservas);


        return "reservas";
    }

    @GetMapping("/nuevaReserva")
    public String mostrarFormularioDeNuevaReserva(Model modelo) {
        List<Mesa> listaMesas = mesaServicio.obtenerTodasMesas();
        modelo.addAttribute("listaMesas", listaMesas);
        modelo.addAttribute("reserva", new Reserva());
        modelo.addAttribute("horariosDisponibles", obtenerHorariosDisponibles());
        return "nueva_reserva";
    }


    @PostMapping("/guardar")
    public String guardarReserva(@ModelAttribute("reserva") Reserva reserva) {
        // Generar un código aleatorio (puedes personalizar la lógica según tus necesidades)
        String codigoAleatorio = generarCodigoAleatorio();


        // Asignar el código a la reserva
        reserva.setCodigo(codigoAleatorio);

        // Obtener la mesa seleccionada
        Optional<Mesa> mesaSeleccionada = Optional.ofNullable(reserva.getMesa());


        // Verificar si la mesa seleccionada existe y obtener su capacidad
        mesaSeleccionada.ifPresent(mesa -> reserva.setCantPers(mesa.getCapacidad()));

        // Asignar automáticamente el rango de horario seleccionado a la reserva
        String rangoHorarioSeleccionado = reserva.getRangoHorario();
        asignarHorariosReserva(reserva, rangoHorarioSeleccionado);

        // Guardar la reserva
        reservaServicio.guardarReserva(reserva);

        return "redirect:/reservas/buscarReserva";
    }
    // Método para generar un código aleatorio
    private String generarCodigoAleatorio() {
        // Puedes personalizar la lógica para generar un código único y aleatorio
        // Aquí un ejemplo básico usando java.util.UUID
        return UUID.randomUUID().toString().substring(0, 8);
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEdicionReserva(@PathVariable Long id, Model modelo) {
        Optional<Reserva> reserva = reservaServicio.obtenerReservaPorId(id);
        List<Mesa> listaMesas = mesaServicio.obtenerMesasDisponibles();

        if (reserva.isPresent()) {
            modelo.addAttribute("reserva", reserva.get());
            modelo.addAttribute("listaMesas", listaMesas);
            modelo.addAttribute("horariosDisponibles", obtenerHorariosDisponibles());
            return "editar_reserva";
        } else {
            // Manejar el caso de reserva no encontrada
            return "redirect:/reservas";
        }
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarReserva(@PathVariable Long id, @ModelAttribute("reserva") Reserva reserva) {
        // Puedes agregar validaciones antes de actualizar si es necesario
        reservaServicio.guardarReserva(reserva);
        return "redirect:/reservas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarReserva(@PathVariable Long id) {
        // Obtener la reserva a eliminar
        Optional<Reserva> reservaOptional = reservaServicio.obtenerReservaPorId(id);

        if (reservaOptional.isPresent()) {
            Reserva reserva = reservaOptional.get();

            // Obtener la mesa asociada a la reserva
            Mesa mesa = reserva.getMesa();

            // Establecer el estado de la mesa como "DISPONIBLE"
            mesa.setEstado("DISPONIBLE");
            mesaServicio.guardarMesa(mesa);

            // Eliminar la reserva
            reservaServicio.eliminarReserva(id);
        }
        return "redirect:/reservas/buscarReserva";
    }

    @GetMapping("/buscarReserva")
    public String mostrarFormularioBusqueda() {
        return "buscar_reserva";
    }

    @GetMapping("/buscarReserva/{tipo}/{valor}")
    @ResponseBody
    public List<Reserva> buscarReserva(@PathVariable String tipo, @PathVariable String valor) {
        List<Reserva> reservas = new ArrayList<>();

        if ("dni".equals(tipo)) {
            reservas = reservaServicio.obtenerReservaPorDni(valor);
        } else if ("codigo".equals(tipo)) {
            reservas = reservaServicio.obtenerReservaPorCodigo(valor);
        }

        return reservas;
    }

    @GetMapping("/obtenerHorariosDisponibles")
    @ResponseBody
    public List<String> obtenerHorariosDisponibles(@RequestParam("fecha") String fechaStr, @RequestParam("mesaId") Long mesaId) {
        LocalDate fecha = LocalDate.parse(fechaStr);

        // Obtener los horarios ocupados para la mesa y fecha seleccionadas
        List<String> horariosOcupados = reservaServicio.obtenerHorariosOcupadosPorMesaYFecha(mesaId, fecha);

        // Filtrar los horarios disponibles
        List<String> horariosDisponibles = obtenerHorariosDisponibles()
                .stream()
                .filter(horario -> !horariosOcupados.contains(horario))
                .collect(Collectors.toList());

        return horariosDisponibles;
    }



    // Método para obtener los horarios disponibles
    private List<String> obtenerHorariosDisponibles() {
        // Puedes personalizar la lógica según tus necesidades
        return List.of("18hs a 20hs", "20hs a 22hs", "22hs a 00hs");
    }

    // Método para asignar automáticamente los horarios a la reserva
    private void asignarHorariosReserva(Reserva reserva, String rangoHorario) {
        // Puedes personalizar la lógica según tus necesidades
        switch (rangoHorario) {
            case "18hs a 20hs":
                reserva.setHoraInicio("18:00");
                reserva.setHoraFin("20:00");
                break;
            case "20hs a 22hs":
                reserva.setHoraInicio("20:00");
                reserva.setHoraFin("22:00");
                break;
            case "22hs a 00hs":
                reserva.setHoraInicio("22:00");
                reserva.setHoraFin("00:00");
                break;
            // Puedes agregar más casos según sea necesario
        }
    }


}
