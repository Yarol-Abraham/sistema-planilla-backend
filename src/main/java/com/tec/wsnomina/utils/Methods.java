package com.tec.wsnomina.utils;

public class Methods {

	private String SUCCESS = "00";
	private String ERROR = "-1";
	private String RESOURCE_NOT_FOUND = "01";

	private int RESET_ACCESS = 0;
	private int STATUS_UP = 1;
	private int STATUS_DOWN = 3;
	private int STATUS_LOCKED = 2;
		
	public String GETRESOURCE_NOT_FOUND() {
		return RESOURCE_NOT_FOUND;
	}

	public String GETSUCCESS() {
		return SUCCESS;
	}
	
	public String GETERROR() {
		return ERROR;
	}

	public int GETRESET_ACCESS() {
		return RESET_ACCESS;
	}
	
	public int GETSTATUS_UP() {
		return STATUS_UP;
	}

	public int GETSTATUS_DOWN() {
		return STATUS_DOWN;
	}

	public int GETSTATUS_LOCKED() {
		return STATUS_LOCKED;
	}
	
}
