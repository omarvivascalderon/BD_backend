package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Equipo;
import com.example.demo.repository.EquipoRepository;

@Service
public class EquipoService {

    @Autowired
    private EquipoRepository repository;

    public List<Equipo> equipos() {
        return repository.findAll();
    }

    public Equipo crearEquipo(Equipo equipo) {
        return repository.save(equipo);
    }
}
