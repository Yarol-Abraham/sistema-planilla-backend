package com.tec.wsnomina.dto;

import java.util.Date;


public class PersonaDto {

    private int idPersona;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private int idGenero;
    private String genero;
    private String direccion;
    private String telefono;
    private String correoElectronico;
    private String estadoCivil;
    private int idEstadoCivil;
    
    public PersonaDto() {}
	public PersonaDto(int idPersona, String nombre, String apellido, Date fechaNacimiento, int idGenero,
			String genero, String direccion, String telefono, String correoElectronico, String estadoCivil,
			int idEstadoCivil) {
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.idGenero = idGenero;
		this.genero = genero;
		this.direccion = direccion;
		this.telefono = telefono;
		this.correoElectronico = correoElectronico;
		this.estadoCivil = estadoCivil;
		this.idEstadoCivil = idEstadoCivil;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
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

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getIdGenero() {
		return idGenero;
	}

	public void setIdGenero(int idGenero) {
		this.idGenero = idGenero;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public int getIdEstadoCivil() {
		return idEstadoCivil;
	}

	public void setIdEstadoCivil(int idEstadoCivil) {
		this.idEstadoCivil = idEstadoCivil;
	}
    

    public boolean validarCampos() {
        return validarNombre() && validarApellido() && validarFechaNacimiento()
                && validarGenero() && validarDireccion() && validarTelefono()
                && validarCorreoElectronico() && validarEstadoCivil();
    }

    private boolean validarNombre() {
        return nombre != null && !nombre.isEmpty();
    }

    private boolean validarApellido() {
        return apellido != null && !apellido.isEmpty();
    }

    private boolean validarFechaNacimiento() {
        return fechaNacimiento != null;
    }

    private boolean validarGenero() {
        return  idGenero > 0;
    }

    private boolean validarDireccion() {
        return direccion != null && !direccion.isEmpty();
    }

    private boolean validarTelefono() {
        return telefono != null && !telefono.isEmpty();
    }

    private boolean validarCorreoElectronico() {
        return correoElectronico != null && !correoElectronico.isEmpty();
    }

    private boolean validarEstadoCivil() {
        return idEstadoCivil > 0;
    }
    
    
}
