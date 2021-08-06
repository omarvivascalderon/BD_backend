package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Partido;

@Repository
public interface PartidosRepository extends JpaRepository<Partido, Long> {
	List<Partido> findAllByOrderByFechaDesc();
}
