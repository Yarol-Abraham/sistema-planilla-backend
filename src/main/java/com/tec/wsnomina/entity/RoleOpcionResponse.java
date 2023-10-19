package com.tec.wsnomina.entity;

import com.tec.wsnomina.dto.OptionDto;

public class RoleOpcionResponse {

	private String strResponseCode = "";
	private String strResponseMessage = "";
	private OptionDto option = new OptionDto();
	
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
	public OptionDto getOption() {
		return option;
	}
	public void setOption(OptionDto option) {
		this.option = option;
	}
	
}
