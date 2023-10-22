package com.tec.wsnomina.dto;

import java.math.BigDecimal;
import java.util.Date;

public class EmpleadoDto {


	private int idEmpleado;
    private PersonaDto persona;
    private SucursalDto sucursal;
    private Date fechaContratacion;
    private PuestoDto puesto;
    private int idStatusEmpleado;
    private BigDecimal ingresoSueldoBase;
    private BigDecimal ingresoBonificacionDecreto;
    private BigDecimal ingresoOtrosIngresos;
    private BigDecimal descuentoIgss;
    private BigDecimal descuentoIsr;
    private BigDecimal descuentoInasistencias;
	
    
    public EmpleadoDto(int idEmpleado, PersonaDto persona, SucursalDto sucursal, Date fechaContratacion,
			PuestoDto puesto, int idStatusEmpleado, BigDecimal ingresoSueldoBase, BigDecimal ingresoBonificacionDecreto,
			BigDecimal ingresoOtrosIngresos, BigDecimal descuentoIgss, BigDecimal descuentoIsr,
			BigDecimal descuentoInasistencias) {
	
		this.idEmpleado = idEmpleado;
		this.persona = persona;
		this.sucursal = sucursal;
		this.fechaContratacion = fechaContratacion;
		this.puesto = puesto;
		this.idStatusEmpleado = idStatusEmpleado;
		this.ingresoSueldoBase = ingresoSueldoBase;
		this.ingresoBonificacionDecreto = ingresoBonificacionDecreto;
		this.ingresoOtrosIngresos = ingresoOtrosIngresos;
		this.descuentoIgss = descuentoIgss;
		this.descuentoIsr = descuentoIsr;
		this.descuentoInasistencias = descuentoInasistencias;
	}


	public int getIdEmpleado() {
		return idEmpleado;
	}


	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}


	public PersonaDto getPersona() {
		return persona;
	}


	public void setPersona(PersonaDto persona) {
		this.persona = persona;
	}


	public SucursalDto getSucursal() {
		return sucursal;
	}


	public void setSucursal(SucursalDto sucursal) {
		this.sucursal = sucursal;
	}


	public Date getFechaContratacion() {
		return fechaContratacion;
	}


	public void setFechaContratacion(Date fechaContratacion) {
		this.fechaContratacion = fechaContratacion;
	}


	public PuestoDto getPuesto() {
		return puesto;
	}


	public void setPuesto(PuestoDto puesto) {
		this.puesto = puesto;
	}


	public int getIdStatusEmpleado() {
		return idStatusEmpleado;
	}


	public void setIdStatusEmpleado(int idStatusEmpleado) {
		this.idStatusEmpleado = idStatusEmpleado;
	}


	public BigDecimal getIngresoSueldoBase() {
		return ingresoSueldoBase;
	}


	public void setIngresoSueldoBase(BigDecimal ingresoSueldoBase) {
		this.ingresoSueldoBase = ingresoSueldoBase;
	}


	public BigDecimal getIngresoBonificacionDecreto() {
		return ingresoBonificacionDecreto;
	}


	public void setIngresoBonificacionDecreto(BigDecimal ingresoBonificacionDecreto) {
		this.ingresoBonificacionDecreto = ingresoBonificacionDecreto;
	}


	public BigDecimal getIngresoOtrosIngresos() {
		return ingresoOtrosIngresos;
	}


	public void setIngresoOtrosIngresos(BigDecimal ingresoOtrosIngresos) {
		this.ingresoOtrosIngresos = ingresoOtrosIngresos;
	}


	public BigDecimal getDescuentoIgss() {
		return descuentoIgss;
	}


	public void setDescuentoIgss(BigDecimal descuentoIgss) {
		this.descuentoIgss = descuentoIgss;
	}


	public BigDecimal getDescuentoIsr() {
		return descuentoIsr;
	}


	public void setDescuentoIsr(BigDecimal descuentoIsr) {
		this.descuentoIsr = descuentoIsr;
	}


	public BigDecimal getDescuentoInasistencias() {
		return descuentoInasistencias;
	}


	public void setDescuentoInasistencias(BigDecimal descuentoInasistencias) {
		this.descuentoInasistencias = descuentoInasistencias;
	}
    
   
	
	
}
