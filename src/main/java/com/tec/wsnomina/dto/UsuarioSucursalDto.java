package com.tec.wsnomina.dto;

public class UsuarioSucursalDto  extends UsuarioCreateDto {

	public String nombreSucursal = "";
	public int idStatusUsuario = 0;
	
	public UsuarioSucursalDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UsuarioSucursalDto(String nombre, String apellido, String correo, String telefono, String fechaNacimiento,
			int genero, String idUsuario, int idSucursal,String nombreSucursal, int requiereCambiarPassword, String fotografia, int idStatusUsuario) {
		super(nombre, apellido, correo, telefono, fechaNacimiento, genero, idUsuario, idSucursal, requiereCambiarPassword,
				fotografia);
		
		this.nombreSucursal =nombreSucursal;
		this.idStatusUsuario = idStatusUsuario;
		// TODO Auto-generated constructor stub
	}

	public String getNombreSucursal() {
		return nombreSucursal;
	}

	public void setNombreSucursal(String nombreSucursal) {
		this.nombreSucursal = nombreSucursal;
	}

	public int getIdStatusUsuario() {
		return idStatusUsuario;
	}

	public void setIdStatusUsuario(int idStatusUsuario) {
		this.idStatusUsuario = idStatusUsuario;
	}

	
}
