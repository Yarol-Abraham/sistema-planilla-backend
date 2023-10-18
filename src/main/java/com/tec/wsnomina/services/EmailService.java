package com.tec.wsnomina.services;


import java.util.Optional;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import com.tec.wsnomina.dto.EmailBodyDto;
import com.tec.wsnomina.entity.EmailResponse;
import com.tec.wsnomina.entity.UsuarioEntity;
import com.tec.wsnomina.repository.IUsuarioRepository;
import com.tec.wsnomina.security.GenerateToken;
import com.tec.wsnomina.security.PasswordEncrypt;
import com.tec.wsnomina.utils.Methods;
import com.tec.wsnomina.utils.Utils;

import io.jsonwebtoken.Claims;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService implements EmailPort {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

	@Autowired
	JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	
	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl = new UsuarioServiceImpl();
	
	@Autowired
	private IUsuarioRepository iUsuarioRepository;
	
	private UsuarioEntity user;
	
	private Utils utils = new Utils();
	private Methods methods = new Methods();
	private PasswordEncrypt passwordEncrypt = new PasswordEncrypt();
	
	private String urlFront = "localhost:5173/auth/confirm-password";
	private String newPassword = "";
	
	public EmailService() {
		
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("de2b0c51916a9a");
        mailSender.setPassword("2584d677c78842");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
    }
	
	public EmailResponse validatedEmail(String email)
	{
		EmailResponse emailResponse = new EmailResponse();
		try
		{
			Optional<UsuarioEntity> user = this.iUsuarioRepository.findByCorreoElectronico(email);
		
			if(user.isEmpty())
			{
				emailResponse.setStrResponseCode(methods.GETERROR());
				emailResponse.setStrResponseMessage("El correo no existe en tec S.A");
				return emailResponse;
			}
			
			 String capPassword = this.usuarioServiceImpl.generatePassword(user.get().getSucursal().getIdSucursal());
			 newPassword = capPassword;
			
			emailResponse.setStrResponseCode(methods.GETSUCCESS());
		}
		catch(Exception ex)
		{
			emailResponse.setStrResponseCode(methods.GETERROR());
			emailResponse.setStrResponseMessage("No se puede validar el correo electrónico, vuelve a intentarlo");
		}
		
		return emailResponse;
	}
	
	public String generateTokenForPassword(String newPassword, String correo) 
	{
		return GenerateToken.createTokenForRecoverPassword(newPassword, correo);
	}
	
	@Override
	public EmailResponse verifyPassword(String token)
	{
		EmailResponse emailResponse = new EmailResponse();
		
		Claims resultVerify = GenerateToken.getTokenForRecoverPassword(token);
		
		if(resultVerify == null)
		{
			emailResponse.setStrResponseCode(methods.GETERROR());
			emailResponse.setStrResponseMessage("token de verificación inválido");
			return emailResponse;
		}
		
		String correo = resultVerify.get("correo").toString();
		String captureNewPassword = resultVerify.get("keyword").toString();
		
		user = this.iUsuarioRepository.findByCorreoElectronico(correo).get();
		
		if(user.equals(null))
		{
			emailResponse.setStrResponseCode(methods.GETERROR());
			emailResponse.setStrResponseMessage("token de verificación inválido o token expirado");
			return emailResponse;	
		}
		
		emailResponse.setStrResponseCode(methods.GETSUCCESS());
		emailResponse.setStrResponseMessage(captureNewPassword);
		return emailResponse;
		
	}
	
	@Override
	public EmailResponse confirmNewPassword(String token) 
	{
		EmailResponse emailResponse = verifyPassword(token);
		try
		{
			if(emailResponse.getStrResponseCode().equals(methods.GETERROR()))
			{
				emailResponse.setStrResponseCode(methods.GETERROR());
				emailResponse.setStrResponseMessage("token de verificación inválido o token expirado");
				return emailResponse;
			}
			
			String captureNewPassword = emailResponse.getStrResponseMessage();
			
			user.setPassword(passwordEncrypt.generateEncrypt(captureNewPassword));
			user.setUltimaFechaCambioPassword(this.utils.getFechaHoraFormateada());
		
			user.setPassword(captureNewPassword);
			user.setUltimaFechaCambioPassword(this.utils.getFechaHoraFormateada());
			this.iUsuarioRepository.save(user);
			
			emailResponse.setStrResponseCode(methods.GETSUCCESS());
			emailResponse.setStrResponseMessage("contraseña restablecida exitosamente");
		}
		catch(Exception ex)
		{
			emailResponse.setStrResponseCode(methods.GETSUCCESS());
			emailResponse.setStrResponseMessage("Error al verificar token");
		}
		return emailResponse;
	}
	
	@Override
	public EmailResponse sendEmail(String email)  
	{
		EmailBodyDto emailBody = new EmailBodyDto();
		emailBody.setEmail(email);
		emailBody.setSubject("Solicitud de recuperación de correo");
		EmailResponse emailResponse = new EmailResponse();
		try
		{
			LOGGER.info("EmailBody: {}", emailBody.toString());
			MimeMessage message = mailSender.createMimeMessage();
			message.setFrom("andbox.smtp.mailtrap.io");
			message.setRecipients(MimeMessage.RecipientType.TO, emailBody.getEmail());
			message.setSubject(emailBody.getSubject());
			
			 emailResponse = validatedEmail(emailBody.getEmail());
			if(emailResponse.getStrResponseCode().equals(methods.GETERROR())) return emailResponse;
			
			String htmlContent = "<!doctype html>\r\n"
					+ "<html>\r\n"
					+ "  <head>\r\n"
					+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\r\n"
					+ "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\r\n"
					+ "    <title>Simple Transactional Email</title>\r\n"
					+ "    <style>\r\n"
					+ "      /* -------------------------------------\r\n"
					+ "          GLOBAL RESETS\r\n"
					+ "      ------------------------------------- */\r\n"
					+ "      \r\n"
					+ "      /*All the styling goes here*/\r\n"
					+ "      \r\n"
					+ "      img {\r\n"
					+ "        border: none;\r\n"
					+ "        -ms-interpolation-mode: bicubic;\r\n"
					+ "        max-width: 100%; \r\n"
					+ "      }\r\n"
					+ "\r\n"
					+ "      body {\r\n"
					+ "        background-color: #f6f6f6;\r\n"
					+ "        font-family: sans-serif;\r\n"
					+ "        -webkit-font-smoothing: antialiased;\r\n"
					+ "        font-size: 14px;\r\n"
					+ "        line-height: 1.4;\r\n"
					+ "        margin: 0;\r\n"
					+ "        padding: 0;\r\n"
					+ "        -ms-text-size-adjust: 100%;\r\n"
					+ "        -webkit-text-size-adjust: 100%; \r\n"
					+ "      }\r\n"
					+ "\r\n"
					+ "      table {\r\n"
					+ "        border-collapse: separate;\r\n"
					+ "        mso-table-lspace: 0pt;\r\n"
					+ "        mso-table-rspace: 0pt;\r\n"
					+ "        width: 100%; }\r\n"
					+ "        table td {\r\n"
					+ "          font-family: sans-serif;\r\n"
					+ "          font-size: 14px;\r\n"
					+ "          vertical-align: top; \r\n"
					+ "      }\r\n"
					+ "\r\n"
					+ "      /* -------------------------------------\r\n"
					+ "          BODY & CONTAINER\r\n"
					+ "      ------------------------------------- */\r\n"
					+ "\r\n"
					+ "      .body {\r\n"
					+ "        background-color: #f6f6f6;\r\n"
					+ "        width: 100%; \r\n"
					+ "      }\r\n"
					+ "\r\n"
					+ "      /* Set a max-width, and make it display as block so it will automatically stretch to that width, but will also shrink down on a phone or something */\r\n"
					+ "      .container {\r\n"
					+ "        display: block;\r\n"
					+ "        margin: 0 auto !important;\r\n"
					+ "        /* makes it centered */\r\n"
					+ "        max-width: 580px;\r\n"
					+ "        padding: 10px;\r\n"
					+ "        width: 580px; \r\n"
					+ "      }\r\n"
					+ "\r\n"
					+ "      /* This should also be a block element, so that it will fill 100% of the .container */\r\n"
					+ "      .content {\r\n"
					+ "        box-sizing: border-box;\r\n"
					+ "        display: block;\r\n"
					+ "        margin: 0 auto;\r\n"
					+ "        max-width: 580px;\r\n"
					+ "        padding: 10px; \r\n"
					+ "      }\r\n"
					+ "\r\n"
					+ "      /* -------------------------------------\r\n"
					+ "          HEADER, FOOTER, MAIN\r\n"
					+ "      ------------------------------------- */\r\n"
					+ "      .main {\r\n"
					+ "        background: #ffffff;\r\n"
					+ "        border-radius: 3px;\r\n"
					+ "        width: 100%; \r\n"
					+ "      }\r\n"
					+ "\r\n"
					+ "      .wrapper {\r\n"
					+ "        box-sizing: border-box;\r\n"
					+ "        padding: 20px; \r\n"
					+ "      }\r\n"
					+ "\r\n"
					+ "      .content-block {\r\n"
					+ "        padding-bottom: 10px;\r\n"
					+ "        padding-top: 10px;\r\n"
					+ "      }\r\n"
					+ "\r\n"
					+ "      .footer {\r\n"
					+ "        clear: both;\r\n"
					+ "        margin-top: 10px;\r\n"
					+ "        text-align: center;\r\n"
					+ "        width: 100%; \r\n"
					+ "      }\r\n"
					+ "        .footer td,\r\n"
					+ "        .footer p,\r\n"
					+ "        .footer span,\r\n"
					+ "        .footer a {\r\n"
					+ "          color: #999999;\r\n"
					+ "          font-size: 12px;\r\n"
					+ "          text-align: center; \r\n"
					+ "      }\r\n"
					+ "\r\n"
					+ "      /* -------------------------------------\r\n"
					+ "          TYPOGRAPHY\r\n"
					+ "      ------------------------------------- */\r\n"
					+ "      h1,\r\n"
					+ "      h2,\r\n"
					+ "      h3,\r\n"
					+ "      h4 {\r\n"
					+ "        color: #000000;\r\n"
					+ "        font-family: sans-serif;\r\n"
					+ "        font-weight: 400;\r\n"
					+ "        line-height: 1.4;\r\n"
					+ "        margin: 0;\r\n"
					+ "        margin-bottom: 30px; \r\n"
					+ "      }\r\n"
					+ "\r\n"
					+ "      h1 {\r\n"
					+ "        font-size: 35px;\r\n"
					+ "        font-weight: 300;\r\n"
					+ "        text-align: center;\r\n"
					+ "        text-transform: capitalize; \r\n"
					+ "      }\r\n"
					+ "\r\n"
					+ "      p,\r\n"
					+ "      ul,\r\n"
					+ "      ol {\r\n"
					+ "        font-family: sans-serif;\r\n"
					+ "        font-size: 14px;\r\n"
					+ "        font-weight: normal;\r\n"
					+ "        margin: 0;\r\n"
					+ "        margin-bottom: 15px; \r\n"
					+ "      }\r\n"
					+ "        p li,\r\n"
					+ "        ul li,\r\n"
					+ "        ol li {\r\n"
					+ "          list-style-position: inside;\r\n"
					+ "          margin-left: 5px; \r\n"
					+ "      }\r\n"
					+ "\r\n"
					+ "      a {\r\n"
					+ "        color: #3498db;\r\n"
					+ "        text-decoration: underline; \r\n"
					+ "      }\r\n"
					+ "\r\n"
					+ "      /* -------------------------------------\r\n"
					+ "          BUTTONS\r\n"
					+ "      ------------------------------------- */\r\n"
					+ "      .btn {\r\n"
					+ "        box-sizing: border-box;\r\n"
					+ "        width: 100%; }\r\n"
					+ "        .btn > tbody > tr > td {\r\n"
					+ "          padding-bottom: 15px; }\r\n"
					+ "        .btn table {\r\n"
					+ "          width: auto; \r\n"
					+ "      }\r\n"
					+ "        .btn table td {\r\n"
					+ "          background-color: #ffffff;\r\n"
					+ "          border-radius: 5px;\r\n"
					+ "          text-align: center; \r\n"
					+ "      }\r\n"
					+ "        .btn a {\r\n"
					+ "          background-color: #ffffff;\r\n"
					+ "          border: solid 1px #3498db;\r\n"
					+ "          border-radius: 5px;\r\n"
					+ "          box-sizing: border-box;\r\n"
					+ "          color: #3498db;\r\n"
					+ "          cursor: pointer;\r\n"
					+ "          display: inline-block;\r\n"
					+ "          font-size: 14px;\r\n"
					+ "          font-weight: bold;\r\n"
					+ "          margin: 0;\r\n"
					+ "          padding: 12px 25px;\r\n"
					+ "          text-decoration: none;\r\n"
					+ "          text-transform: capitalize; \r\n"
					+ "      }\r\n"
					+ "\r\n"
					+ "      .btn-primary table td {\r\n"
					+ "        background-color: #6610f2; \r\n"
					+ "      }\r\n"
					+ "\r\n"
					+ "      .btn-primary a {\r\n"
					+ "        background-color: #6610f2;\r\n"
					+ "        border-color: #6610f2;\r\n"
					+ "        color: #ffffff; \r\n"
					+ "      }\r\n"
					+ "\r\n"
					+ "      /* -------------------------------------\r\n"
					+ "          OTHER STYLES THAT MIGHT BE USEFUL\r\n"
					+ "      ------------------------------------- */\r\n"
					+ "      .last {\r\n"
					+ "        margin-bottom: 0; \r\n"
					+ "      }\r\n"
					+ "\r\n"
					+ "      .first {\r\n"
					+ "        margin-top: 0; \r\n"
					+ "      }\r\n"
					+ "\r\n"
					+ "      .align-center {\r\n"
					+ "        text-align: center; \r\n"
					+ "      }\r\n"
					+ "\r\n"
					+ "      .align-right {\r\n"
					+ "        text-align: right; \r\n"
					+ "      }\r\n"
					+ "\r\n"
					+ "      .align-left {\r\n"
					+ "        text-align: left; \r\n"
					+ "      }\r\n"
					+ "\r\n"
					+ "      .clear {\r\n"
					+ "        clear: both; \r\n"
					+ "      }\r\n"
					+ "\r\n"
					+ "      .mt0 {\r\n"
					+ "        margin-top: 0; \r\n"
					+ "      }\r\n"
					+ "\r\n"
					+ "      .mb0 {\r\n"
					+ "        margin-bottom: 0; \r\n"
					+ "      }\r\n"
					+ "\r\n"
					+ "      .preheader {\r\n"
					+ "        color: transparent;\r\n"
					+ "        display: none;\r\n"
					+ "        height: 0;\r\n"
					+ "        max-height: 0;\r\n"
					+ "        max-width: 0;\r\n"
					+ "        opacity: 0;\r\n"
					+ "        overflow: hidden;\r\n"
					+ "        mso-hide: all;\r\n"
					+ "        visibility: hidden;\r\n"
					+ "        width: 0; \r\n"
					+ "      }\r\n"
					+ "\r\n"
					+ "      .powered-by a {\r\n"
					+ "        text-decoration: none; \r\n"
					+ "      }\r\n"
					+ "\r\n"
					+ "      hr {\r\n"
					+ "        border: 0;\r\n"
					+ "        border-bottom: 1px solid #f6f6f6;\r\n"
					+ "        margin: 20px 0; \r\n"
					+ "      }\r\n"
					+ "\r\n"
					+ "      /* -------------------------------------\r\n"
					+ "          RESPONSIVE AND MOBILE FRIENDLY STYLES\r\n"
					+ "      ------------------------------------- */\r\n"
					+ "      @media only screen and (max-width: 620px) {\r\n"
					+ "        table.body h1 {\r\n"
					+ "          font-size: 28px !important;\r\n"
					+ "          margin-bottom: 10px !important; \r\n"
					+ "        }\r\n"
					+ "        table.body p,\r\n"
					+ "        table.body ul,\r\n"
					+ "        table.body ol,\r\n"
					+ "        table.body td,\r\n"
					+ "        table.body span,\r\n"
					+ "        table.body a {\r\n"
					+ "          font-size: 16px !important; \r\n"
					+ "        }\r\n"
					+ "        table.body .wrapper,\r\n"
					+ "        table.body .article {\r\n"
					+ "          padding: 10px !important; \r\n"
					+ "        }\r\n"
					+ "        table.body .content {\r\n"
					+ "          padding: 0 !important; \r\n"
					+ "        }\r\n"
					+ "        table.body .container {\r\n"
					+ "          padding: 0 !important;\r\n"
					+ "          width: 100% !important; \r\n"
					+ "        }\r\n"
					+ "        table.body .main {\r\n"
					+ "          border-left-width: 0 !important;\r\n"
					+ "          border-radius: 0 !important;\r\n"
					+ "          border-right-width: 0 !important; \r\n"
					+ "        }\r\n"
					+ "        table.body .btn table {\r\n"
					+ "          width: 100% !important; \r\n"
					+ "        }\r\n"
					+ "        table.body .btn a {\r\n"
					+ "          width: 100% !important; \r\n"
					+ "        }\r\n"
					+ "        table.body .img-responsive {\r\n"
					+ "          height: auto !important;\r\n"
					+ "          max-width: 100% !important;\r\n"
					+ "          width: auto !important; \r\n"
					+ "        }\r\n"
					+ "      }\r\n"
					+ "\r\n"
					+ "      /* -------------------------------------\r\n"
					+ "          PRESERVE THESE STYLES IN THE HEAD\r\n"
					+ "      ------------------------------------- */\r\n"
					+ "      @media all {\r\n"
					+ "        .ExternalClass {\r\n"
					+ "          width: 100%; \r\n"
					+ "        }\r\n"
					+ "        .ExternalClass,\r\n"
					+ "        .ExternalClass p,\r\n"
					+ "        .ExternalClass span,\r\n"
					+ "        .ExternalClass font,\r\n"
					+ "        .ExternalClass td,\r\n"
					+ "        .ExternalClass div {\r\n"
					+ "          line-height: 100%; \r\n"
					+ "        }\r\n"
					+ "        .apple-link a {\r\n"
					+ "          color: inherit !important;\r\n"
					+ "          font-family: inherit !important;\r\n"
					+ "          font-size: inherit !important;\r\n"
					+ "          font-weight: inherit !important;\r\n"
					+ "          line-height: inherit !important;\r\n"
					+ "          text-decoration: none !important; \r\n"
					+ "        }\r\n"
					+ "        #MessageViewBody a {\r\n"
					+ "          color: inherit;\r\n"
					+ "          text-decoration: none;\r\n"
					+ "          font-size: inherit;\r\n"
					+ "          font-family: inherit;\r\n"
					+ "          font-weight: inherit;\r\n"
					+ "          line-height: inherit;\r\n"
					+ "        }\r\n"
					+ "        .btn-primary table td:hover {\r\n"
					+ "          background-color: #6610f2 !important; \r\n"
					+ "        }\r\n"
					+ "        .btn-primary a:hover {\r\n"
					+ "          background-color: #6610f2 !important;\r\n"
					+ "          border-color: #6610f2 !important; \r\n"
					+ "        } \r\n"
					+ "      }\r\n"
					+ "\r\n"
					+ "    </style>\r\n"
					+ "  </head>\r\n"
					+ "  <body>\r\n"
					+ "    <span class=\"preheader\">This is preheader text. Some clients will show this text as a preview.</span>\r\n"
					+ "    <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"body\">\r\n"
					+ "      <tr>\r\n"
					+ "        <td>&nbsp;</td>\r\n"
					+ "        <td class=\"container\">\r\n"
					+ "          <div class=\"content\">\r\n"
					+ "\r\n"
					+ "            <!-- START CENTERED WHITE CONTAINER -->\r\n"
					+ "            <table role=\"presentation\" class=\"main\">\r\n"
					+ "\r\n"
					+ "              <!-- START MAIN CONTENT AREA -->\r\n"
					+ "              <tr>\r\n"
					+ "                <td class=\"wrapper\">\r\n"
					+ "                  <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
					+ "                    <tr>\r\n"
					+ "                      <td>\r\n"
					+ "                        <p>Asunto: Recuperación de Contraseña</p>\r\n"
					+ "                        <p>Hemos recibido una solicitud para restablecer la contraseña de tu cuenta. No te preocupes, estamos aquí para ayudarte.</p>\r\n"
					+ "                        <p>Tu contraseña actual sera el siguiente, puedes copiarla y utilizarla en tu siguiente sesión.</p>\r\n"
					+ "                        <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"btn btn-primary\">\r\n"
					+ "                          <tbody>\r\n"
					+ "                            <tr>\r\n"
					+ "                              <td align=\"left\">\r\n"
					+ "                                <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
					+ "                                  <tbody>\r\n"
					+ "                                    <tr>\r\n"
					+ "                                      <td> <a  href=\"http://"+ urlFront+"?token="+generateTokenForPassword(newPassword, emailBody.getEmail())+"\" target=\"_blank\">Click para obtener tu nueva contraseña</a> </td>\r\n"
					+ "                                    </tr>\r\n"
					+ "                                  </tbody>\r\n"
					+ "                                </table>\r\n"
					+ "                              </td>\r\n"
					+ "                            </tr>\r\n"
					+ "                          </tbody>\r\n"
					+ "                        </table>\r\n"
					+ "                        <p>Si no solicitaste este cambio, por favor ignora este mensaje.</p>\r\n"
					+ "                        <p>¡Gracias!</p>\r\n"
					+ "                        <p>El equipo de soporte</p>\r\n"
					+ "                      </td>\r\n"
					+ "                    </tr>\r\n"
					+ "                  </table>\r\n"
					+ "                </td>\r\n"
					+ "              </tr>\r\n"
					+ "\r\n"
					+ "            <!-- END MAIN CONTENT AREA -->\r\n"
					+ "            </table>\r\n"
					+ "            <!-- END CENTERED WHITE CONTAINER -->\r\n"
					+ "\r\n"
					+ "            <!-- START FOOTER -->\r\n"
					+ "            <div class=\"footer\">\r\n"
					+ "              <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
					+ "                <tr>\r\n"
					+ "                  <td class=\"content-block\">\r\n"
					+ "                    <span class=\"apple-link\">Company Tec S.A</span>\r\n"
					+ "                  </td>\r\n"
					+ "                </tr>\r\n"
					+ "                <tr>\r\n"
					+ "                  <td class=\"content-block powered-by\">\r\n"
					+ "                    Este correo fue enviado por mailtrap <a href=\"https://mailtrap.io/es\">Mailtrap</a>.\r\n"
					+ "                  </td>\r\n"
					+ "                </tr>\r\n"
					+ "              </table>\r\n"
					+ "            </div>\r\n"
					+ "            <!-- END FOOTER -->\r\n"
					+ "\r\n"
					+ "          </div>\r\n"
					+ "        </td>\r\n"
					+ "        <td>&nbsp;</td>\r\n"
					+ "      </tr>\r\n"
					+ "    </table>\r\n"
					+ "  </body>\r\n"
					+ "</html>";
			 
	        message.setContent(htmlContent, "text/html; charset=utf-8");
	        mailSender.send(message);
	        
	        emailResponse.setStrResponseCode(methods.GETSUCCESS());
			emailResponse.setStrResponseMessage("Se ha enviado la contraseña de restauracion a tu correo!");
			
		}
		catch(Exception ex)
		{
			LOGGER.error("Hubo un error al enviar el mail: {}", ex);

			emailResponse.setStrResponseCode(methods.GETERROR());
			emailResponse.setStrResponseMessage("Hubo un error al enviar el mail: ");
		}
		return emailResponse;
	}
		
}















