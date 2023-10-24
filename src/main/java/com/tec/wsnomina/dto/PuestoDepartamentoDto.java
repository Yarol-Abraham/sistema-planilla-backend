package com.tec.wsnomina.dto;

public class PuestoDepartamentoDto {

	private int idPuesto;
    private String nombre;
    private DepartamentoDto departamento;
    
	public PuestoDepartamentoDto(int idPuesto, String nombre, DepartamentoDto departamento) 
	{
		this.idPuesto = idPuesto;
		this.departamento = departamento;
		this.nombre = nombre;
	}
	
	public int getIdPuesto() {
		return idPuesto;
	}
	public void setIdPuesto(int idPuesto) {
		this.idPuesto = idPuesto;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public DepartamentoDto getDepartamento() {
		return departamento;
	}

	public void setDepartamento(DepartamentoDto departamento) {
		this.departamento = departamento;
	}

	public boolean validate() 
	{
       
        if (getIdPuesto() <= 0) {
            System.out.println("El ID del puesto debe ser un número positivo.");
            return false;
        }
        
        if (getNombre() == null || getNombre().isEmpty()) {
            System.out.println("El nombre del puesto no puede estar vacío.");
            return false;
        }

        if (getDepartamento().getIdDepartamento() <= 0) {
            System.out.println("El ID del departamento debe ser un número positivo.");
            return false;
        }

        return true;    
    }
	
	
}
