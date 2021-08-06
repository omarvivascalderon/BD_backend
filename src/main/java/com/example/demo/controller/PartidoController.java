package com.example.demo.controller;

import java.sql.Date;
import java.util.Calendar;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Partido;
import com.example.demo.service.PartidoService;
import com.example.demo.util.Util;

@RequestMapping("/api/partido")
@RestController
@Validated
public class PartidoController {

	@Autowired
	private PartidoService service;

	private Util util = new Util();

	@SuppressWarnings("rawtypes")
	@GetMapping("/buscar")
	public ResponseEntity verPartidos() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.partidos());
		} catch (ConstraintViolationException ex) {
			return util.responseViolationException(ex, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return util.responseException(e, HttpStatus.INTERNAL_SERVER_ERROR, false);
		}
	}

	@SuppressWarnings("rawtypes")
	@PostMapping("/crear")
	public ResponseEntity registrarPartidos(@RequestBody Partido partido) {
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(partido.getFecha());
			c.add(Calendar.DAY_OF_MONTH, 1);
			partido.setFecha(new Date(c.getTimeInMillis()));
			return ResponseEntity.status(HttpStatus.OK).body(service.crear(partido));
		} catch (ConstraintViolationException ex) {
			return util.responseViolationException(ex, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return util.responseException(e, HttpStatus.INTERNAL_SERVER_ERROR, false);
		}
	}

	@SuppressWarnings("rawtypes")
	@PutMapping("/actualizar")
	public ResponseEntity actualizarPartidos(@RequestBody Partido partido) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.actualizar(partido));
		} catch (ConstraintViolationException ex) {
			return util.responseViolationException(ex, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return util.responseException(e, HttpStatus.INTERNAL_SERVER_ERROR, false);
		}
	}
}
