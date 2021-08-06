package com.example.demo.controller;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Equipo;
import com.example.demo.service.EquipoService;
import com.example.demo.util.Util;

@RequestMapping("/api/equipo")
@RestController
@Validated
public class EquipoController {

	@Autowired
	private EquipoService service;

	private Util util = new Util();

	@SuppressWarnings("rawtypes")
	@GetMapping("/buscar")
	public ResponseEntity obtenerEquipo() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.equipos());
		} catch (ConstraintViolationException ex) {
			return util.responseViolationException(ex, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return util.responseException(e, HttpStatus.INTERNAL_SERVER_ERROR, false);
		}
	}

	@SuppressWarnings("rawtypes")
	@PostMapping("/crear")
	public ResponseEntity crearEquipo(@RequestBody Equipo equipo) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.crearEquipo(equipo));
		} catch (ConstraintViolationException ex) {
			return util.responseViolationException(ex, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return util.responseException(e, HttpStatus.INTERNAL_SERVER_ERROR, false);
		}
	}

}
