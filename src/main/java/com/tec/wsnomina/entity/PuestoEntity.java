package com.tec.wsnomina.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "puesto")
public class PuestoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdPuesto")
    private int idPuesto;

    @Column(name = "Nombre", nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "IdDepartamento", referencedColumnName = "IdDepartamento", insertable = false, updatable = false)
    private DepartamentoEntity departamento;

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

    @OneToMany(mappedBy = "puesto")
    private List<EmpleadoEntity> empleados;

   
}