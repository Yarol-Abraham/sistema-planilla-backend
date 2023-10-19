package com.tec.wsnomina.entity;

import java.util.List;

import com.tec.wsnomina.dto.MenuDto;

public class MenuListResponse {

	private String strResponseCode = "";
	private String strResponseMessage = "";
	private List<MenuDto> menus;
	
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
	public List<MenuDto> getMenus() {
		return menus;
	}
	public void setMenus(List<MenuDto> menus) {
		this.menus = menus;
	}
	
	
}
