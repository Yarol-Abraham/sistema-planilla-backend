package com.tec.wsnomina.entity;

import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "menu")
public class MenuEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdMenu")
	private int idMenu;
	
	@ManyToOne
	@JoinColumn(name = "IdModulo", referencedColumnName = "IdModulo")
	private ModuloEntity modulo;
	
	@Column(name = "Nombre")
	private String nombre;
	
	@Column(name = "OrdenMenu")
	private int ordenMenu;
	
	@Column(name = "FechaCreacion")
	private String fechaCreacion = "";
   
	@Column(name = "UsuarioCreacion")
	private String usuarioCreacion;
   
	@Column(name = "FechaModificacion")
	private String fechaModificacion = null;
   
	@Column(name = "UsuarioModificacion")
	private String usuarioModificacion = null;

	@OneToMany(mappedBy = "menu")
	private List<OptionEntity> opciones;
	
	public ModuloEntity getModulo() {
		return modulo;
	}

	public void setModulo(ModuloEntity modulo) {
		this.modulo = modulo;
	}

	public int getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(int idMenu) {
		this.idMenu = idMenu;
	}
/*
	public int getIdModulo() {
		return idModulo;
	}

	public void setIdModulo(int idModulo) {
		this.idModulo = idModulo;
	}
	*/

	public List<OptionEntity> getOpciones() {
		return opciones;
	}

	public void setOpciones(List<OptionEntity> opciones) {
		this.opciones = opciones;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getOrdenMenu() {
		return ordenMenu;
	}

	public void setOrdenMenu(int ordenMenu) {
		this.ordenMenu = ordenMenu;
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
	
	
}
