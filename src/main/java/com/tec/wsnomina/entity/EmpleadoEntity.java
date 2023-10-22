package com.tec.wsnomina.entity;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "empleado")
public class EmpleadoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdEmpleado")
    private int idEmpleado;

    @ManyToOne
    @JoinColumn(name = "IdPersona", referencedColumnName = "IdPersona")
    private PersonaEntity persona;

    @ManyToOne
    @JoinColumn(name = "IdSucursal", referencedColumnName = "IdSucursal")
    private SucursalEntity sucursal;

    @Column(name = "FechaContratacion", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaContratacion;

    @ManyToOne
    @JoinColumn(name = "IdPuesto", referencedColumnName = "IdPuesto")
    private PuestoEntity puesto;

    @ManyToOne
    @JoinColumn(name = "IdStatusEmpleado", referencedColumnName = "IdStatusEmpleado")
    private StatusEmpleadoEntity statusEmpleado;

    @Column(name = "IngresoSueldoBase", nullable = false, precision = 10, scale = 2)
    private BigDecimal ingresoSueldoBase;

    @Column(name = "IngresoBonificacionDecreto", nullable = false, precision = 10, scale = 2)
    private BigDecimal ingresoBonificacionDecreto;

    @Column(name = "IngresoOtrosIngresos", nullable = false, precision = 10, scale = 2)
    private BigDecimal ingresoOtrosIngresos;

    @Column(name = "DescuentoIgss", nullable = false, precision = 10, scale = 2)
    private BigDecimal descuentoIgss;

    @Column(name = "DescuentoIsr", nullable = false, precision = 10, scale = 2)
    private BigDecimal descuentoIsr;

    @Column(name = "DescuentoInasistencias", nullable = false, precision = 10, scale = 2)
    private BigDecimal descuentoInasistencias;

    @Column(name = "FechaCreacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Column(name = "UsuarioCreacion", nullable = false)
    private String usuarioCreacion;

    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;

    @Column(name = "UsuarioModificacion")
    private String usuarioModificacion;

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public PersonaEntity getPersona() {
		return persona;
	}

	public void setPersona(PersonaEntity persona) {
		this.persona = persona;
	}

	public SucursalEntity getSucursal() {
		return sucursal;
	}

	public void setSucursal(SucursalEntity sucursal) {
		this.sucursal = sucursal;
	}

	public Date getFechaContratacion() {
		return fechaContratacion;
	}

	public void setFechaContratacion(Date fechaContratacion) {
		this.fechaContratacion = fechaContratacion;
	}

	public PuestoEntity getPuesto() {
		return puesto;
	}

	public void setPuesto(PuestoEntity puesto) {
		this.puesto = puesto;
	}

	public StatusEmpleadoEntity getStatusEmpleado() {
		return statusEmpleado;
	}

	public void setStatusEmpleado(StatusEmpleadoEntity statusEmpleado) {
		this.statusEmpleado = statusEmpleado;
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

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getUsuarioModificacion() {
		return usuarioModificacion;
	}

	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

    
    
}