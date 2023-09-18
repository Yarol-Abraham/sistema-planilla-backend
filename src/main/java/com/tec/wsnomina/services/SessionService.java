package com.tec.wsnomina.services;

import com.tec.wsnomina.entity.SessionCredentials;
import com.tec.wsnomina.entity.SessionInformationResponse;

public interface SessionService {

	public SessionInformationResponse getByInformationUserSesion(String SessionId);
	public SessionInformationResponse generateSessionByUser(SessionCredentials sessionCredentials);
	
}
