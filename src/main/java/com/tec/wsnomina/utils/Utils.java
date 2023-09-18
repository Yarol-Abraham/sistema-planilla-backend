package com.tec.wsnomina.utils;

import java.net.InetAddress;

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

	
	 /**
     * return Get format date with hour
     */
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
    
    /*
     * return Get format date
     * */
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
}
