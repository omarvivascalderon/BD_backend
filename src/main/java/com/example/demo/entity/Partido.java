package com.example.demo.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "partidos")
public class Partido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "usuario")
	@NotNull
	private Integer usuario;

	@Column(name = "local")
	@NotNull
	private Integer local;

	@Column(name = "visitante")
	@NotNull
	private Integer visitante;

	@Column(name = "fecha")
	@NotNull
	private Date fecha;

	@Column(name = "goles_visitante")
	@NotNull
	private Integer golesVisitante;

	@Column(name = "goles_local")
	@NotNull
	private Integer golesLocal;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario", insertable = false, updatable = false)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Usuario usuarioModel;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "local", insertable = false, updatable = false)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Equipo localModel;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "visitante", insertable = false, updatable = false)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Equipo visitanteModel;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getUsuario() {
		return usuario;
	}

	public void setUsuario(Integer usuario) {
		this.usuario = usuario;
	}

	public Integer getLocal() {
		return local;
	}

	public void setLocal(Integer local) {
		this.local = local;
	}

	public Integer getVisitante() {
		return visitante;
	}

	public void setVisitante(Integer visitante) {
		this.visitante = visitante;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getGolesVisitante() {
		return golesVisitante;
	}

	public void setGolesVisitante(Integer golesVisitante) {
		this.golesVisitante = golesVisitante;
	}

	public Integer getGolesLocal() {
		return golesLocal;
	}

	public void setGolesLocal(Integer golesLocal) {
		this.golesLocal = golesLocal;
	}

	public Usuario getUsuarioModel() {
		return usuarioModel;
	}

	public void setUsuarioModel(Usuario usuarioModel) {
		this.usuarioModel = usuarioModel;
	}

	public Equipo getLocalModel() {
		return localModel;
	}

	public void setLocalModel(Equipo localModel) {
		this.localModel = localModel;
	}

	public Equipo getVisitanteModel() {
		return visitanteModel;
	}

	public void setVisitanteModel(Equipo visitanteModel) {
		this.visitanteModel = visitanteModel;
	}

}
