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
    @JoinColumn(name = "IdPersona", referencedColumnName = "IdPersona", insertable = false, updatable = false)
    private PersonaEntity persona;

    @ManyToOne
    @JoinColumn(name = "IdSucursal", referencedColumnName = "IdSucursal", insertable = false, updatable = false)
    private SucursalEntity sucursal;

    @Column(name = "FechaContratacion", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaContratacion;

    @ManyToOne
    @JoinColumn(name = "IdPuesto", referencedColumnName = "IdPuesto", insertable = false, updatable = false)
    private PuestoEntity puesto;

    @ManyToOne
    @JoinColumn(name = "IdStatusEmpleado", referencedColumnName = "IdStatusEmpleado", insertable = false, updatable = false)
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

    // Getters y setters

    // Otros métodos y relaciones según sea necesario
}