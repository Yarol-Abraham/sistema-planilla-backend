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
@Table(name = "empresa")
public class EmpresaEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdEmpresa")
	private int idEmpresa;
	
	@Column(name = "Nombre")
	private String nombre = "";
	
	@Column(name = "Direccion")
	private String direccion = "";
	
	@Column(name = "Nit")
	private String nit = "";
	
	@Column(name = "PasswordCantidadMayusculas")
	private int passwordCantidadMayusculas;
	
	@Column(name = "PasswordCantidadMinusculas")
	private int passwordCantidadMinusculas;
	
	@Column(name = "PasswordCantidadCaracteresEspeciales")
	private int passwordCantidadCaracteresEspeciales;
	
	@Column(name = "PasswordCantidadCaducidadDias")
	private int passwordCantidadCaducidadDias;
	
	@Column(name = "PasswordLargo")
	private int passwordLargo;
	
	@Column(name = "PasswordIntentosAntesDeBloquear")
	private int passwordIntentosAntesDeBloquear;
	
	@Column(name = "PasswordCantidadNumeros")
	private int passwordCantidadNumeros;
	
	@Column(name ="PasswordCantidadPreguntasValidar")
	private int passwordCantidadPreguntasValidar;
	
	@Column(name = "FechaCreacion")
	private String fechaCreacion = "";
   
	@Column(name = "UsuarioCreacion")
	private String usuarioCreacion;
   
	@Column(name = "FechaModificacion")
	private String fechaModificacion = null;
   
	@Column(name = "UsuarioModificacion")
	private String usuarioModificacion = null;
   
	@OneToMany(mappedBy = "empresa")
	private List<SucursalEntity> sucursales;
	
	
	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public int getPasswordCantidadMayusculas() {
		return passwordCantidadMayusculas;
	}

	public void setPasswordCantidadMayusculas(int passwordCantidadMayusculas) {
		this.passwordCantidadMayusculas = passwordCantidadMayusculas;
	}

	public int getPasswordCantidadMinusculas() {
		return passwordCantidadMinusculas;
	}

	public void setPasswordCantidadMinusculas(int passwordCantidadMinusculas) {
		this.passwordCantidadMinusculas = passwordCantidadMinusculas;
	}

	public int getPasswordCantidadCaracteresEspeciales() {
		return passwordCantidadCaracteresEspeciales;
	}

	public void setPasswordCantidadCaracteresEspeciales(int passwordCantidadCaracteresEspeciales) {
		this.passwordCantidadCaracteresEspeciales = passwordCantidadCaracteresEspeciales;
	}

	public int getPasswordCantidadCaducidadDias() {
		return passwordCantidadCaducidadDias;
	}

	public void setPasswordCantidadCaducidadDias(int passwordCantidadCaducidadDias) {
		this.passwordCantidadCaducidadDias = passwordCantidadCaducidadDias;
	}

	public int getPasswordLargo() {
		return passwordLargo;
	}

	public void setPasswordLargo(int passwordLargo) {
		this.passwordLargo = passwordLargo;
	}

	public int getPasswordIntentosAntesDeBloquear() {
		return passwordIntentosAntesDeBloquear;
	}

	public void setPasswordIntentosAntesDeBloquear(int passwordIntentosAntesDeBloquear) {
		this.passwordIntentosAntesDeBloquear = passwordIntentosAntesDeBloquear;
	}

	public int getPasswordCantidadNumeros() {
		return passwordCantidadNumeros;
	}

	public void setPasswordCantidadNumeros(int passwordCantidadNumeros) {
		this.passwordCantidadNumeros = passwordCantidadNumeros;
	}

	public int getPasswordCantidadPreguntasValidar() {
		return passwordCantidadPreguntasValidar;
	}

	public void setPasswordCantidadPreguntasValidar(int passwordCantidadPreguntasValidar) {
		this.passwordCantidadPreguntasValidar = passwordCantidadPreguntasValidar;
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
