package com.tec.wsnomina.entity;

import com.tec.wsnomina.entity.key.RoleOptionKey;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "role_opcion")
public class RoleOptionEntity {

	@EmbeddedId
	private RoleOptionKey id;
	
	@ManyToOne
	@JoinColumn(name = "IdOpcion", referencedColumnName = "IdOpcion", insertable = false, updatable = false)
	private OptionEntity opcion;
	
	@Column(name = "Alta")
	private int alta;
	
	@Column(name = "Baja")
	private int baja;
	
	@Column(name = "Cambio")
	private int cambio;
	
	@Column(name = "Imprimir")
	private int imprimir;
	
	@Column(name = "Exportar")
	private int exportar;
	
	@Column(name = "FechaCreacion")
	private String fechaCreacion = "";
	
	@Column(name = "UsuarioCreacion")
	private String usuarioCreacion = "";
	
	@Column(name = "FechaModificacion")
	private String fechaModificacion = null;
	
	@Column(name = "UsuarioModificacion")
	private String usuarioModificacion = null;

	public RoleOptionKey getId() {
		return id;
	}

	public void setId(RoleOptionKey id) {
		this.id = id;
	}

	public OptionEntity getOpcion() {
		return opcion;
	}

	public void setOpcion(OptionEntity opcion) {
		this.opcion = opcion;
	}

	public int getAlta() {
		return alta;
	}

	public void setAlta(int alta) {
		this.alta = alta;
	}

	public int getBaja() {
		return baja;
	}

	public void setBaja(int baja) {
		this.baja = baja;
	}

	public int getCambio() {
		return cambio;
	}

	public void setCambio(int cambio) {
		this.cambio = cambio;
	}

	public int getImprimir() {
		return imprimir;
	}

	public void setImprimir(int imprimir) {
		this.imprimir = imprimir;
	}

	public int getExportar() {
		return exportar;
	}

	public void setExportar(int exportar) {
		this.exportar = exportar;
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

