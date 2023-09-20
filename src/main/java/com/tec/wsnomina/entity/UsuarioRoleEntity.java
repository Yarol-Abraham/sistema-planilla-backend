package com.tec.wsnomina.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario_role")
public class UsuarioRoleEntity {

	@Id
	@Column(name = "IdUsuario")
	private String idUsuario = "";
	
	@Column(name = "IdRole")
	private String idRole = "";
	
	@Column(name = "FechaCreacion")
	private String fechaCreacion;
	
	@Column(name = "UsuarioCreacion")
	private String usuarioCreacion;
	
	@Column(name = "FechaModificacion")
	private String fechaModificacion;
	
	@Column(name = "UsuarioModificacion")
	private String usuarioModificacion;

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getIdRole() {
		return idRole;
	}

	public void setIdRole(String idRole) {
		this.idRole = idRole;
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

	@Override
	public String toString() {
		return "UsuarioRoleEntity [idUsuario=" + idUsuario + ", idRole=" + idRole + ", fechaCreacion=" + fechaCreacion
				+ ", usuarioCreacion=" + usuarioCreacion + ", fechaModificacion=" + fechaModificacion
				+ ", usuarioModificacion=" + usuarioModificacion + "]";
	}

}
