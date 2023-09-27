package com.tec.wsnomina.entity;

import java.util.ArrayList;
import java.util.List;

import com.tec.wsnomina.dto.RoleDto;

public class RoleListResponse {

	private String strResponseCode = "";
	private String strResponseMessage = "";
	private List<RoleDto> roles = new ArrayList<RoleDto>();
	
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
	public List<RoleDto> getRoles() {
		return roles;
	}
	public void setRoles(List<RoleDto> roles) {
		this.roles = roles;
	}
	
}
