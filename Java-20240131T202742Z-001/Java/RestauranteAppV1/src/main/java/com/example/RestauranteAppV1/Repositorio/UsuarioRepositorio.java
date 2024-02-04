package com.example.RestauranteAppV1.Repositorio;

import com.example.RestauranteAppV1.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    List<Usuario> findByRol_RolId(Long rolId);
    Usuario findByUsername(String username);

}