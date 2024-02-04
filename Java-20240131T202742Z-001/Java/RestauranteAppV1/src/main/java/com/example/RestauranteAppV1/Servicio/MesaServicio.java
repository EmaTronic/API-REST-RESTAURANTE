package com.example.RestauranteAppV1.Servicio;

import com.example.RestauranteAppV1.Entidades.Mesa;
import com.example.RestauranteAppV1.Repositorio.MesaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MesaServicio {
    @Autowired
    private MesaRepositorio mesaRepositorio;

    public List<Mesa> obtenerTodasMesas() {
        return mesaRepositorio.findAll();
    }
    public List<Mesa> obtenerMesasDisponibles() {
        return mesaRepositorio.findByEstado("DISPONIBLE");
    }

    public Mesa obtenerMesaPorId(Long id) {
        return mesaRepositorio.findById(id).orElse(null);
    }

    public void guardarMesa(Mesa mesa) {
        mesaRepositorio.save(mesa);
    }

    public void eliminarMesa(Long id) {
        mesaRepositorio.deleteById(id);
    }
}
