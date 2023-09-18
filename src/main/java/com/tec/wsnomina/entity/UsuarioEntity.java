package com.tec.wsnomina.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class UsuarioEntity {

   @Id
   @Column(name = "IdUsuario")
   private String idUsuario = "";
   
   @Column(name = "Nombre")
   private String nombre = "";
  
   @Column(name = "Apellido")
   private String apellido= "";
   
   @Column(name = "FechaNacimiento")
   private String fechaNacimiento = null;
   
   @Column(name = "IdStatusUsuario")
   private int idStatusUsuario = 1;
   
   @Column(name = "Password")
   private String password = "";
   
   @Column(name = "IdGenero")
   private int idGenero;
   
   @Column(name = "UltimaFechaIngreso")
   private String ultimaFechaIngreso = null;
   
   @Column(name = "IntentosDeAcceso")
   private int intentosDeAcceso = 0;
   
   @Column(name = "SesionActual")
   private String sesionActual = null;
   
   @Column(name = "UltimaFechaCambioPassword")
   private String ultimaFechaCambioPassword = null;
   
   @Column(name = "CorreoElectronico")
   private String correoElectronico = "";
   
   @Column(name = "RequiereCambiarPassword")
   private int requiereCambiarPassword = 0;

   @Column(name = "Fotografia")
   private String fotografia = null;
   
   @Column(name = "TelefonoMovil")
   private String telefonoMovil = "";
   
   @Column(name = "IdSucursal")
   private int idSucursal = 0;
   
   @Column(name = "FechaCreacion")
   private String fechaCreacion = "";
   
   @Column(name = "UsuarioCreacion")
   private String usuarioCreacion;
   
   @Column(name = "FechaModificacion")
   private String fechaModificacion = null;
   
   @Column(name = "UsuarioModificacion")
   private String usuarioModificacion = null;

	public String getIdUsuario() {
		return idUsuario;
	}
	
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public int getIdStatusUsuario() {
		return idStatusUsuario;
	}
	
	public void setIdStatusUsuario(int idStatusUsuario) {
		this.idStatusUsuario = idStatusUsuario;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getIdGenero() {
		return idGenero;
	}
	
	public void setIdGenero(int idGenero) {
		this.idGenero = idGenero;
	}
	
	public String getUltimaFechaIngreso() {
		return ultimaFechaIngreso;
	}
	
	public void setUltimaFechaIngreso(String ultimaFechaIngreso) {
		this.ultimaFechaIngreso = ultimaFechaIngreso;
	}
	
	public int getIntentosDeAcceso() {
		return intentosDeAcceso;
	}
	
	public void setIntentosDeAcceso(int intentosDeAcceso) {
		this.intentosDeAcceso = intentosDeAcceso;
	}
	
	public String getSesionActual() {
		return sesionActual;
	}
	
	public void setSesionActual(String sesionActual) {
		this.sesionActual = sesionActual;
	}
	
	public String getUltimaFechaCambioPassword() {
		return ultimaFechaCambioPassword;
	}
	
	public void setUltimaFechaCambioPassword(String ultimaFechaCambioPassword) {
		this.ultimaFechaCambioPassword = ultimaFechaCambioPassword;
	}
	
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	
	public int getRequiereCambiarPassword() {
		return requiereCambiarPassword;
	}
	
	public void setRequiereCambiarPassword(int requiereCambiarPassword) {
		this.requiereCambiarPassword = requiereCambiarPassword;
	}
	
	public String getFotografia() {
		return fotografia;
	}
	
	public void setFotografia(String fotografia) {
		this.fotografia = fotografia;
	}
	
	public String getTelefonoMovil() {
		return telefonoMovil;
	}
	
	public void setTelefonoMovil(String telefonoMovil) {
		this.telefonoMovil = telefonoMovil;
	}
	
	public int getIdSucursal() {
		return idSucursal;
	}
	
	public void setIdSucursal(int idSucursal) {
		this.idSucursal = idSucursal;
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
