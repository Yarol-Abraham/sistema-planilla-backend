package com.tec.wsnomina.entity;

import java.util.List;

import com.tec.wsnomina.dto.RoleDto;

public class SessionInformationResponse {

	private String strResponseCode = "";
	private String strResponseMessage = "";
	private String strSessionId = "";
	private String strIdUsuario = "";
	private String strNombre = "";
	private String strFotografia = "";
	private int intRoleSelect = 0;
	private List<RoleDto> listRoles;
	
	public  List<RoleDto> getListRoles() {
		return listRoles;
	}
	public void setListRoles(List<RoleDto> listRoles) {
		this.listRoles = listRoles;
	}
	public int getIntRoleSelect() {
		return intRoleSelect;
	}
	public void setIntRoleSelect(int intRole) {
		this.intRoleSelect = intRole;
	}
	public String getStrSessionId() {
		return strSessionId;
	}
	public void setStrSessionId(String strSessionId) {
		this.strSessionId = strSessionId;
	}
	public String getStrIdUsuario() {
		return strIdUsuario;
	}
	public void setStrIdUsuario(String strIdUsuario) {
		this.strIdUsuario = strIdUsuario;
	}
	public String getStrNombre() {
		return strNombre;
	}
	public void setStrNombre(String strNombre) {
		this.strNombre = strNombre;
	}
	public String getStrFotografia() {
		return strFotografia;
	}
	public void setStrFotografia(String strFotografia) {
		this.strFotografia = strFotografia;
	}
	public String getStrResponseCode() {
		return strResponseCode;
	}
	
	public void setStrResponseCode(String strResponseCode) {
		this.strResponseCode = strResponseCode;
	}
	public String getStrResponseMessage() {
		return strResponseMessage;
	}
	public void setStrResponseMessage(String strResponseMessage) {
		this.strResponseMessage = strResponseMessage;
	}
	
	
	
}
