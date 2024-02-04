package com.example.RestauranteAppV1.Servicio;

import com.example.RestauranteAppV1.Entidades.Rol;
import com.example.RestauranteAppV1.Entidades.Usuario;
import com.example.RestauranteAppV1.Repositorio.RolRepositorio;
import com.example.RestauranteAppV1.Repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolServicio {
@Autowired
    private RolRepositorio rolRepositorio;


    public List<Rol> obtenerTodosRoles() {
        return rolRepositorio.findAll();
    }

    public Rol obtenerRolPorId(Long id) {
        return rolRepositorio.findById(id).orElse(null);
    }

    public void guardarRol(Rol rol) {
        rolRepositorio.save(rol);
    }

    public void eliminarRol(Long id) {
        rolRepositorio.deleteById(id);
    }
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    public boolean existenUsuariosConRol(Long rolId) {
        List<Usuario> usuariosConRol = usuarioRepositorio.findByRol_RolId(rolId);
        return !usuariosConRol.isEmpty();
    }

}
