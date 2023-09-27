package com.tec.wsnomina.entity;


import com.tec.wsnomina.dto.RoleDto;

public class RoleResponse {
	private String strResponseCode = "";
	private String strResponseMessage = "";
	private RoleDto role = new RoleDto();
	
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
	public RoleDto getRole() {
		return role;
	}
	public void setRole(RoleDto role) {
		this.role = role;
	}
			
}
