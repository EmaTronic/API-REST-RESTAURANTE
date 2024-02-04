package com.example.RestauranteAppV1.Servicio;

import com.example.RestauranteAppV1.Entidades.Ciudad;
import com.example.RestauranteAppV1.Entidades.Provincia;
import com.example.RestauranteAppV1.Repositorio.CiudadRepositorio;
import com.example.RestauranteAppV1.Repositorio.ProvinciaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinciaServicio {
    @Autowired
    private ProvinciaRepositorio provinciaRepositorio;

    public List<Provincia> obtenerTodasProvincias() {
        return provinciaRepositorio.findAll();
    }

    public void guardarProvincia(Provincia provincia) {
        provinciaRepositorio.save(provincia);
    }

    public Provincia obtenerProvinciaPorId(Long id) {
        return provinciaRepositorio.findById(id).orElse(null);
    }

    public void eliminarProvincia(Long id) {
        provinciaRepositorio.deleteById(id);
    }

    @Autowired
    private CiudadRepositorio ciudadRepositorio;

    public boolean existenCiudadesEnProvincia(Long provinciaId) {
        List<Ciudad> ciudadesEnProvincia = ciudadRepositorio.findByProvincia_ProvinciaId(provinciaId);
        return !ciudadesEnProvincia.isEmpty();
    }


    public List<Provincia> obtenerProvinciasPorPais(Long paisId) {
        return provinciaRepositorio.findByPais_PaisId(paisId);
    }


}
