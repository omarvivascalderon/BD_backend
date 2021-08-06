package com.example.demo.service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Partido;
import com.example.demo.repository.PartidosRepository;

@Service
public class PartidoService {

	@Autowired
	private PartidosRepository repository;

	public List<Partido> partidos() {
		return repository.findAllByOrderByFechaDesc();
	}

	public Partido crear(Partido partido) throws Exception {
		return repository.save(partido);
	}

	public Partido actualizar(Partido partido) {
		Calendar c = Calendar.getInstance();
		c.setTime(partido.getFecha());
		c.add(Calendar.DAY_OF_MONTH, 1);
		partido.setFecha(new Date(c.getTimeInMillis()));
		Partido partidoExistente = repository.findById(partido.getId()).orElse(null);
		BeanUtils.copyProperties(partido, partidoExistente);
		partidoExistente.setId(partido.getId());
		return repository.save(partidoExistente);
	}

	public Long eliminar(Long id) {
		repository.deleteById(id);
		return id;
	}
}
