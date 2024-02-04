package com.example.RestauranteAppV1.Servicio;

import com.example.RestauranteAppV1.Entidades.Ciudad;
import com.example.RestauranteAppV1.Entidades.Provincia;
import com.example.RestauranteAppV1.Repositorio.CiudadRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CiudadServicio {

    @Autowired
    private CiudadRepositorio ciudadRepositorio;

    public List<Ciudad> obtenerTodasCiudades() {
        return ciudadRepositorio.findAll();
    }

    public Ciudad obtenerCiudadPorId(Long id) {
        return ciudadRepositorio.findById(id).orElse(null);
    }
    public void guardarCiudad(Ciudad ciudad) {
        ciudadRepositorio.save(ciudad);
    }

    public void eliminarCiudad(Long id) {
        ciudadRepositorio.deleteById(id);
    }

public List<Ciudad> obtenerCiudadesPorProvincia(Long provinciaId){
    return ciudadRepositorio.findByProvincia_ProvinciaId(provinciaId);
}

}
