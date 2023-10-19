package com.tec.wsnomina.entity;

import java.util.List;

import com.tec.wsnomina.dto.OptionDto;

public class RoleListOpcionResponse {

	private String strResponseCode = "";
	private String strResponseMessage = "";
	private List<OptionDto> option;
	
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
	public List<OptionDto> getOption() {
		return option;
	}
	public void setOption(List<OptionDto> option) {
		this.option = option;
	}
	
	
}
