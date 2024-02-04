package com.example.RestauranteAppV1.Servicio;



import com.example.RestauranteAppV1.Entidades.Pais;
import com.example.RestauranteAppV1.Entidades.Provincia;
import com.example.RestauranteAppV1.Repositorio.PaisRepositorio;
import com.example.RestauranteAppV1.Repositorio.ProvinciaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaisServicio {
    @Autowired
    private PaisRepositorio paisRepositorio;

    public List<Pais> obtenerTodosPaises() {
        return paisRepositorio.findAll();
    }

    public void guardarPais(Pais pais) {
        paisRepositorio.save(pais);
    }

    public Pais obtenerPaisPorId(Long id) {
        return paisRepositorio.findById(id).orElse(null);
    }

    public void eliminarPais(Long id) {
        paisRepositorio.deleteById(id);
    }

    @Autowired
    private ProvinciaRepositorio provinciaRepositorio;

    public boolean existenProvinciasEnPais(Long paisId) {
        List<Provincia> provinciasEnPais = provinciaRepositorio.findByPais_PaisId(paisId);
        return !provinciasEnPais.isEmpty();
    }
}

