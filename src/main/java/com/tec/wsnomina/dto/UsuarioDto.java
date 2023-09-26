package com.tec.wsnomina.dto;


public class UsuarioDto {

	private String nombre;
	private String apellido;
	private String correoElectronico;
	private String telefonoMovil;
	private String fechaNacimiento;
	private int idGenero;
	
	public UsuarioDto() {}
	
	public UsuarioDto(String nombre, String apellido, String correo, String telefono, String fechaNacimiento,
			int genero) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.correoElectronico = correo;
		this.telefonoMovil = telefono;
		this.fechaNacimiento = fechaNacimiento;
		this.idGenero = genero;
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

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getTelefonoMovil() {
		return telefonoMovil;
	}

	public void setTelefonoMovil(String telefonoMovil) {
		this.telefonoMovil = telefonoMovil;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getIdGenero() {
		return idGenero;
	}

	public void setIdGenero(int idGenero) {
		this.idGenero = idGenero;
	}
	
	
	
	
}
