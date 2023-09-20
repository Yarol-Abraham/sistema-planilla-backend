package com.tec.wsnomina.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "opcion")
public class OptionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdOpcion")
	private int idOpcion;
	
	@Column(name = "IdMenu")
	private int idMenu;
	
	@Column(name = "Nombre")
	private String nombre;

	@Column(name = "OrdenMenu")
	private int ordenMenu;
	
	@Column(name = "Pagina")
	private String pagina;
	
	@Column(name = "FechaCreacion")
	private String fechaCreacion = "";
   
	@Column(name = "UsuarioCreacion")
	private String usuarioCreacion;
   
	@Column(name = "FechaModificacion")
	private String fechaModificacion = null;
   
	@Column(name = "UsuarioModificacion")
	private String usuarioModificacion = null;

	public int getIdOpcion() {
		return idOpcion;
	}

	public void setIdOpcion(int idOpcion) {
		this.idOpcion = idOpcion;
	}

	public int getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(int idMenu) {
		this.idMenu = idMenu;
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

	public String getPagina() {
		return pagina;
	}

	public void setPagina(String pagina) {
		this.pagina = pagina;
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
