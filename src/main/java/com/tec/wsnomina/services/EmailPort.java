package com.tec.wsnomina.services;

import com.tec.wsnomina.dto.EmailBodyDto;

public interface EmailPort {

	public boolean sendEmail(EmailBodyDto emailBody);

}
