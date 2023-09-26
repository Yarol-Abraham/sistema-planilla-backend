package com.tec.wsnomina.dto;

public class UsuarioCreateDto extends UsuarioDto {

	private String idUsuario;
	private int idSucursal;
	private int requiereCambiarPassword = 0;
	private String fotografia = null;

	public UsuarioCreateDto() {}
	
	public UsuarioCreateDto(String nombre, String apellido, String correo, String telefono, String fechaNacimiento,
			int genero,String idUsuario, int idSucursal, int requiereCambiarPassword, String fotografia) {
		super(nombre, apellido, correo, telefono, fechaNacimiento, genero);
		this.idUsuario = idUsuario;
		this.idSucursal = idSucursal;
		this.requiereCambiarPassword = requiereCambiarPassword;
		this.fotografia = fotografia;
	}

	public String getFotografia() {
		return fotografia;
	}

	public void setFotografia(String fotografia) {
		this.fotografia = fotografia;
	}

	public int getRequiereCambiarPassword() {
		return requiereCambiarPassword;
	}

	public void setRequiereCambiarPassword(int requiereCambiarPassword) {
		this.requiereCambiarPassword = requiereCambiarPassword;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdSucursal()
	{
		return idSucursal;
	}

	public void setIdSucursal(int idSucursal) 
	{
		this.idSucursal = idSucursal;
	}
}
