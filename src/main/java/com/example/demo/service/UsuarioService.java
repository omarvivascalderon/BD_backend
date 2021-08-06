package com.example.demo.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	public List<Usuario> usuarios() {
		return repository.findAll();
	}

	public List<Usuario> buscar() {
		return repository.findAll();
	}

	public Usuario login(Usuario usuario) {
		Usuario us = new Usuario();
		List<Usuario> result = repository.findByUsernameAndPassword(usuario.getUsername(), usuario.getPassword());
		if (result != null && result.size() > 0) {
			us = result.get(0);
		}
		return us;
	}
	
	public Usuario findByUsername(String username) {
		return repository.findByUsername(username);
	}
	
	public Boolean existByUsername(String username) {
		Usuario res = repository.findByUsername(username);
		if(res != null) {
			return true;
		}
		return false;
	}

	public Usuario crear(Usuario usuario) {
		return repository.save(usuario);
	}

	public Usuario actualizar(Usuario usuario) {
		Usuario usuarioExistente = repository.findById(usuario.getId()).orElse(null);
		BeanUtils.copyProperties(usuario, usuarioExistente);
		usuarioExistente.setId(usuario.getId());
		return repository.save(usuarioExistente);
	}

	public Long eliminar(Long id) {
		repository.deleteById(id);
		return id;
	}
}
