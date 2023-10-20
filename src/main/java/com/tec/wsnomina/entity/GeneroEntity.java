package com.tec.wsnomina.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "genero")
public class GeneroEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdGenero")
	private int idGenero;
	
	@Column(name = "Nombre")
	private String nombre = "";
	
	@Column(name = "FechaCreacion")
	private String fechaCreacion = "";
	
	@Column(name = "UsuarioCreacion")
	private String usuarioCreacion = "";
	
	@Column(name = "FechaModificacion")
	private String fechaModificacion = null;
	
	@Column(name = "UsuarioModificacion")
	private String usuarioModificacion = null;

	@OneToMany(mappedBy = "genero")
    private List<PersonaEntity> personas;
	
	public int getIdGenero() {
		return idGenero;
	}

	public void setIdGenero(int idGenero) {
		this.idGenero = idGenero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public String getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(String fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getUsuarioModificacion() {
		return usuarioModificacion;
	}

	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	public List<PersonaEntity> getPersonas() {
		return personas;
	}

	public void setPersonas(List<PersonaEntity> personas) {
		this.personas = personas;
	}

	
	
}
