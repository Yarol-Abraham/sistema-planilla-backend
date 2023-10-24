package com.tec.wsnomina.dto;

public class PuestoCreateDto {

	private int idDepartamento = 0;
	private String nombre = "";
	
	public PuestoCreateDto(String nombre, int idDepartamento)
	{
		this.nombre = nombre;
		this.idDepartamento = idDepartamento;
	}

	public int getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(int idDepartamento) {
		this.idDepartamento = idDepartamento;
	}
	
	public String getNombre()
	{
		return nombre;
	}
	
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	
	public boolean validate() 
	{
       
        if (getNombre() == null || getNombre().isEmpty()) {
            System.out.println("El nombre del puesto no puede estar vacío.");
            return false;
        }

        if (getIdDepartamento() <= 0) {
            System.out.println("El ID del departamento debe ser un número positivo.");
            return false;
        }

        return true;    
    }
}
