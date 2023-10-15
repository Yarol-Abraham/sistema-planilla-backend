package com.tec.wsnomina.utils;

import java.net.InetAddress;
import java.util.Random;

import com.tec.wsnomina.dto.ValidatePasswordDto;

public class Utils {
	
	public String getLocalIpAddress() 
	 {
	    String ipAddress = "";
	    try {
	        InetAddress ownIP = InetAddress.getLocalHost();
	        ipAddress = ownIP.getHostAddress();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return ipAddress;
	 }
	
	public String clean(String cadena) 
	{
	    String abecedario = "0123456789ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz-=[*<_.!¡@#$¿>%&]+/()?áéíóúàèìòùü,;";
	    String respuesta = "";
	    for (int i = 0; i < cadena.length(); i++) {
	        String caracter = cadena.charAt(i) + "";
	        if (abecedario.contains(caracter)) {
	            respuesta = respuesta + caracter;
	        }
	    }
	    return respuesta;
	}
	
	public ValidatePasswordDto validatedPassword(int length, int minNumber, int minLowercase, int minUppercase, int minCharacterEspecial, String password) 
	{
		ValidatePasswordDto validatePasswordDto = new ValidatePasswordDto();
		
		 String lowercaseChars = "abcdefghijklmnopqrstuvwxyz";
         String uppercaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
         String digitChars = "0123456789";
         String specialChars = "!@#$%^&*()_+[]{}|;':,.<>?";
         
         int validatedQuantity = 0;
         
         if(password.length() < length) 
         {
        	 validatePasswordDto.setMessage("La contraseña debe de tener un minimo de: " + String.valueOf(length) + " caracteres ");
        	 validatePasswordDto.setValid(false);
        	 return validatePasswordDto;
         }
         
         for (int i = 0; i < password.length(); i++) 
         {
 	        String caracter =  password.charAt(i) + "";
 	        if (digitChars.contains(caracter)) 
 	        {
 	            validatedQuantity++;
 	        }
 	    }
        
        if(validatedQuantity < minNumber)
        {
        	 validatePasswordDto.setMessage("La contraseña debe de tener un minimo de: " + String.valueOf(minNumber) + " numeros ");
        	 validatePasswordDto.setValid(false);
        	 return validatePasswordDto;
        }
        
        validatedQuantity = 0;
        
        for (int i = 0; i < password.length(); i++) 
        {
	        String caracter =  password.charAt(i) + "";
	        if (lowercaseChars.contains(caracter)) 
	        {
	            validatedQuantity++;
	        }
	    }
        
        if(validatedQuantity < minLowercase)
        {
        	 validatePasswordDto.setMessage("La contraseña debe de tener un minimo de: " + String.valueOf(minLowercase) + " caracteres minúsculas ");
        	 validatePasswordDto.setValid(false);
        	 return validatePasswordDto;
        }
        
        validatedQuantity = 0;
        
        for (int i = 0; i < password.length(); i++) 
        {
	        String caracter = password.charAt(i) + "";
	        if (uppercaseChars.contains(caracter)) 
	        {
	            validatedQuantity++;
	        }
	    }
        
        if(validatedQuantity < minUppercase)
        {
        	 validatePasswordDto.setMessage("La contraseña debe de tener un minimo de: " + String.valueOf(minUppercase) + " caracteres mayúsculas ");
        	 validatePasswordDto.setValid(false);
        	 return validatePasswordDto;
        }
        
        validatedQuantity = 0;
        
        for (int i = 0; i < password.length(); i++) 
        {
	        String caracter = password.charAt(i) + "";
	        if (specialChars.contains(caracter)) 
	        {
	            validatedQuantity++;
	        }
	    }
        
        if(validatedQuantity < minCharacterEspecial)
        {
        	 validatePasswordDto.setMessage("La contraseña debe de tener un minimo de: " + String.valueOf(minCharacterEspecial) + " caracteres especiales ");
        	 validatePasswordDto.setValid(false);
        	 return validatePasswordDto;
        }
        
        validatePasswordDto.setMessage("");
   	 	validatePasswordDto.setValid(true);
        return validatePasswordDto;
	}
	
    public String getFechaHoraFormateada() 
    {
        java.util.GregorianCalendar today = new java.util.GregorianCalendar();

        String fechaHora = "";
        fechaHora = String.format("%04d", today.get(java.util.GregorianCalendar.YEAR));
        fechaHora += "-";
        fechaHora += String.format("%02d", (today.get(java.util.GregorianCalendar.MONTH) + 1));
        fechaHora += "-";
        fechaHora += String.format("%02d", today.get(java.util.GregorianCalendar.DAY_OF_MONTH));
        fechaHora += " ";
        fechaHora += String.format("%02d", today.get(java.util.GregorianCalendar.HOUR_OF_DAY));
        fechaHora += ":";
        fechaHora += String.format("%02d", today.get(java.util.GregorianCalendar.MINUTE));
        fechaHora += ":";
        fechaHora += String.format("%02d", today.get(java.util.GregorianCalendar.SECOND));

        return fechaHora;
    }
  
    public String getFecha() 
    {
        java.util.GregorianCalendar today = new java.util.GregorianCalendar();

        String fecha = "";
        fecha = String.format("%04d", today.get(java.util.GregorianCalendar.YEAR));
        fecha += "/";
        fecha += String.format("%02d", (today.get(java.util.GregorianCalendar.MONTH) + 1));
        fecha += "/";
        fecha += String.format("%02d", today.get(java.util.GregorianCalendar.DAY_OF_MONTH));

        return fecha;
    }
    
    public int validNumber(String strnumber)
	{
		int format = -1;
		try
		{
			format = Integer.parseInt(strnumber);
		}
		catch(Exception ex)
		{
			System.out.println("ERROR AL CONVERTIR NUMERO: validNumber(): " + ex.getMessage());
			format = -1;
		}
		
		return format;
	}
    
    public String generatePassword(int length, int minNumber, int minLowercase, int minUppercase, int minCharacterEspecial)
    {
    	if (length < minLowercase + minUppercase + minNumber + minCharacterEspecial) 
    	{
            throw new IllegalArgumentException("La longitud de la contraseña es insuficiente para cumplir con los requisitos.");
        }
    	
    	 String lowercaseChars = "abcdefghijklmnopqrstuvwxyz";
         String uppercaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
         String digitChars = "0123456789";
         String specialChars = "!@#$%^&*()_+[]{}|;':,.<>?";
         
         StringBuilder password = new StringBuilder();
         Random random = new Random();
         
         for(int i = 0; i < minNumber; i++)
         {
        	 password.append(digitChars.charAt(random.nextInt(digitChars.length())));
         }
         
         for(int i = 0; i < minLowercase; i++)
         {
        	 password.append(lowercaseChars.charAt(random.nextInt(lowercaseChars.length())));
         }
         
         for(int i = 0; i < minUppercase; i++)
         {
        	 password.append(uppercaseChars.charAt(random.nextInt(uppercaseChars.length())));
         }
         
         for(int i = 0; i < minCharacterEspecial; i++)
         {
        	 password.append(specialChars.charAt(random.nextInt(specialChars.length())));
         }
         
         String allowedChars = lowercaseChars + uppercaseChars + digitChars + specialChars;
         for (int i = minLowercase + minUppercase + minNumber + minCharacterEspecial; i < length; i++) {
             password.append(allowedChars.charAt(random.nextInt(allowedChars.length())));
         }
         
         char[] passwordArray = password.toString().toCharArray();
         for (int i = passwordArray.length - 1; i > 0; i--) {
             int index = random.nextInt(i + 1);
             char temp = passwordArray[index];
             passwordArray[index] = passwordArray[i];
             passwordArray[i] = temp;
         }
         
         return new String(passwordArray);
    }
    
    
}
