package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con el username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(usuario.getUsername(), usuario.getPassword(),
                new ArrayList<>());
    }

    public Usuario save(UsuarioDTO usuario) {
    	Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsername(usuario.getUsername());
        nuevoUsuario.setPassword(bcryptEncoder.encode(usuario.getPassword()));
        nuevoUsuario.setCorreo(usuario.getCorreo());
        nuevoUsuario.setNombre(usuario.getNombre());
        return usuarioRepository.save(nuevoUsuario);
    }

    public List<Usuario> usuarios() {
        return usuarioRepository.findAll();
    }
}
