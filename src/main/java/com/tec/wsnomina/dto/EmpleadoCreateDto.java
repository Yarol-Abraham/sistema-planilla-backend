package com.tec.wsnomina.dto;

import java.math.BigDecimal;
import java.util.Date;

public class EmpleadoCreateDto {

	private int idEmpleado;
    private int idPersona;
    private int idSucursal;
    private int idPuesto;
    private Date fechaContratacion;
    private int idStatusEmpleado;
    private BigDecimal ingresoSueldoBase;
    private BigDecimal ingresoBonificacionDecreto;
    private BigDecimal ingresoOtrosIngresos;
    private BigDecimal descuentoIgss;
    private BigDecimal descuentoIsr;
    private BigDecimal descuentoInasistencias;
    
    public EmpleadoCreateDto(){}
    
	public EmpleadoCreateDto(int idPersona, int idSucursal, Date fechaContratacion, int idPuesto,
			int idStatusEmpleado, BigDecimal ingresoSueldoBase, BigDecimal ingresoBonificacionDecreto,
			BigDecimal ingresoOtrosIngresos, BigDecimal descuentoIgss, BigDecimal descuentoIsr,
			BigDecimal descuentoInasistencias) 
	{
		
		this.idPersona = idPersona;
		this.idSucursal = idSucursal;
		this.fechaContratacion = fechaContratacion;
		this.idPuesto = idPuesto;
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
	public int getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	public int getIdSucursal() {
		return idSucursal;
	}
	public void setIdSucursal(int idSucursal) {
		this.idSucursal = idSucursal;
	}
	
	public Date getFechaContratacion() {
		return fechaContratacion;
	}
	public void setFechaContratacion(Date fechaContratacion) {
		this.fechaContratacion = fechaContratacion;
	}
	public int getIdPuesto() {
		return idPuesto;
	}
	public void setIdPuesto(int idPuesto) {
		this.idPuesto = idPuesto;
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

	public boolean validarDatos() {
        return  validarIdPersona() && validarIdSucursal()
                && validarFechaContratacion() && validarIdPuesto() && validarIdStatusEmpleado()
                && validarIngresoSueldoBase() && validarIngresoBonificacionDecreto()
                && validarIngresoOtrosIngresos() && validarDescuentoIgss() && validarDescuentoIsr()
                && validarDescuentoInasistencias();
    }

    private boolean validarIdPersona() {
        return idPersona > 0;
    }

    private boolean validarIdSucursal() {
        return idSucursal > 0;
    }

    private boolean validarFechaContratacion() {
        return fechaContratacion != null;
    }

    private boolean validarIdPuesto() {
        return idPuesto > 0;
    }

    private boolean validarIdStatusEmpleado() {
        return idStatusEmpleado > 0;
    }

    private boolean validarIngresoSueldoBase() {
        return ingresoSueldoBase != null && ingresoSueldoBase.compareTo(BigDecimal.ZERO) >= 0;
    }

    private boolean validarIngresoBonificacionDecreto() {
        return ingresoBonificacionDecreto != null && ingresoBonificacionDecreto.compareTo(BigDecimal.ZERO) >= 0;
    }

    private boolean validarIngresoOtrosIngresos() {
        return ingresoOtrosIngresos != null && ingresoOtrosIngresos.compareTo(BigDecimal.ZERO) >= 0;
    }

    private boolean validarDescuentoIgss() {
        return descuentoIgss != null && descuentoIgss.compareTo(BigDecimal.ZERO) >= 0;
    }

    private boolean validarDescuentoIsr() {
        return descuentoIsr != null && descuentoIsr.compareTo(BigDecimal.ZERO) >= 0;
    }

    private boolean validarDescuentoInasistencias() {
        return descuentoInasistencias != null && descuentoInasistencias.compareTo(BigDecimal.ZERO) >= 0;
    }
	
}
