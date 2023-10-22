package com.tec.wsnomina.entity;

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
@Table(name = "bitacora_acceso")
public class BitacoraAccesoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdBitacoraAcceso")
    private int idBitacoraAcceso;

    @Column(name = "IdUsuario", nullable = false)
    private String idUsuario;

    @ManyToOne
    @JoinColumn(name = "IdTipoAcceso", referencedColumnName = "IdTipoAcceso", insertable = false, updatable = false)
    private TipoAccesoEntity tipoAcceso;

    @Column(name = "FechaAcceso", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAcceso;

    @Column(name = "HttpUserAgent", length = 200)
    private String httpUserAgent;

    @Column(name = "DireccionIp", length = 50)
    private String direccionIp;

    @Column(name = "Accion", length = 100)
    private String accion;

    @Column(name = "SistemaOperativo", length = 50)
    private String sistemaOperativo;

    @Column(name = "Dispositivo", length = 50)
    private String dispositivo;

    @Column(name = "Browser", length = 50)
    private String browser;

    @Column(name = "Sesion", length = 100)
    private String sesion;

	public int getIdBitacoraAcceso() {
		return idBitacoraAcceso;
	}

	public void setIdBitacoraAcceso(int idBitacoraAcceso) {
		this.idBitacoraAcceso = idBitacoraAcceso;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public TipoAccesoEntity getTipoAcceso() {
		return tipoAcceso;
	}

	public void setTipoAcceso(TipoAccesoEntity tipoAcceso) {
		this.tipoAcceso = tipoAcceso;
	}

	public Date getFechaAcceso() {
		return fechaAcceso;
	}

	public void setFechaAcceso(Date fechaAcceso) {
		this.fechaAcceso = fechaAcceso;
	}

	public String getHttpUserAgent() {
		return httpUserAgent;
	}

	public void setHttpUserAgent(String httpUserAgent) {
		this.httpUserAgent = httpUserAgent;
	}

	public String getDireccionIp() {
		return direccionIp;
	}

	public void setDireccionIp(String direccionIp) {
		this.direccionIp = direccionIp;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getSistemaOperativo() {
		return sistemaOperativo;
	}

	public void setSistemaOperativo(String sistemaOperativo) {
		this.sistemaOperativo = sistemaOperativo;
	}

	public String getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(String dispositivo) {
		this.dispositivo = dispositivo;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getSesion() {
		return sesion;
	}

	public void setSesion(String sesion) {
		this.sesion = sesion;
	}

    
    
}