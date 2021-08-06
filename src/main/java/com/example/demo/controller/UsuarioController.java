package com.example.demo.controller;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Usuario;
import com.example.demo.service.UsuarioService;
import com.example.demo.util.Util;

@RequestMapping("/api/usuario")
@RestController
@Validated
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	private Util util = new Util();

	@SuppressWarnings("rawtypes")
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody Usuario usuario) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.login(usuario));
		} catch (ConstraintViolationException ex) {
			return util.responseViolationException(ex, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return util.responseException(e, HttpStatus.INTERNAL_SERVER_ERROR, false);
		}
	}

	@SuppressWarnings("rawtypes")
	@GetMapping("/buscar")
	public ResponseEntity buscar() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.buscar());
		} catch (ConstraintViolationException ex) {
			return util.responseViolationException(ex, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return util.responseException(e, HttpStatus.INTERNAL_SERVER_ERROR, false);
		}
	}

	@SuppressWarnings("rawtypes")
	@PostMapping("/crear")
	public ResponseEntity crear(@RequestBody Usuario usuario) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.crear(usuario));
		} catch (ConstraintViolationException ex) {
			return util.responseViolationException(ex, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return util.responseException(e, HttpStatus.INTERNAL_SERVER_ERROR, false);
		}
	}

	@SuppressWarnings("rawtypes")
	@PutMapping("/actualizar")
	public ResponseEntity actualizar(@RequestBody Usuario usuario) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.actualizar(usuario));
		} catch (ConstraintViolationException ex) {
			return util.responseViolationException(ex, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return util.responseException(e, HttpStatus.INTERNAL_SERVER_ERROR, false);
		}
	}

	@SuppressWarnings("rawtypes")
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity actualizar(@PathVariable Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.eliminar(id));
		} catch (ConstraintViolationException ex) {
			return util.responseViolationException(ex, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return util.responseException(e, HttpStatus.INTERNAL_SERVER_ERROR, false);
		}
	}
}
