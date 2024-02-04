package com.example.RestauranteAppV1.Security;

import com.example.RestauranteAppV1.Servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UsuarioServicio usuarioServicio;


    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            com.example.RestauranteAppV1.Entidades.Usuario usuario = usuarioServicio.obtenerUsuarioPorUsername(username);

            if (usuario == null) {
                throw new UsernameNotFoundException("Usuario no encontrado.");
            }

            return User.withUsername(usuario.getUsername())
                    .password(usuario.getClave())
                    .roles(usuario.getRol().getNombre()) // Puedes modificar esto según tu lógica de roles
                    .build();
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Usamos BCrypt para encriptar las contraseñas
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                        auth -> auth.requestMatchers("/login").permitAll()
                                .requestMatchers("/images/**").permitAll()
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/reservas").permitAll()
                                .requestMatchers("/menu").permitAll()
                                .requestMatchers("/reservas/nuevaReserva").permitAll()
                                .requestMatchers("/reservas/buscarReserva").permitAll()
                                .requestMatchers("/reservas/buscarReserva/**").permitAll()
                                .requestMatchers("/reservas/obtenerHorariosDisponibles").permitAll()
                                .requestMatchers("/reservas/guardar").permitAll()
                                .requestMatchers("/reservas/eliminar/**").permitAll()
                                .requestMatchers("/productos/precio/**").permitAll()
                                .requestMatchers("/ciudades/porProvincia/**").permitAll()
                                .requestMatchers("/provincias/porPais/**").permitAll()
                                .requestMatchers("/usuarios/nuevoCliente").permitAll()
                                .requestMatchers("/usuarios/guardarUsuario").permitAll()
                                .requestMatchers("/usuarios").hasRole("ADMIN")
                                //.requestMatchers("/roles").hasRole("CLIENTE")
                                .anyRequest().authenticated() // Requiere autenticación para cualquier otra solicitud

                )

                // Para customizar el login y logout

                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                /*
                .formLogin(withDefaults()) // Utiliza la configuración predeterminada de inicio de sesión
                .logout(withDefaults()) // Utiliza la configuración predeterminada de cierre de sesión
                */

                .exceptionHandling(exceptionHandling ->exceptionHandling
                        .accessDeniedPage("/")
                )
                .httpBasic(withDefaults());


        return http.build();
    }


}






