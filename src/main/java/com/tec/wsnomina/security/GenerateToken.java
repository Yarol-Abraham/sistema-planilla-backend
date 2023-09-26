package com.tec.wsnomina.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Collections;
import io.jsonwebtoken.security.Keys;

public class GenerateToken {
 
     private  static SecretKey generateKey()
     {
    	 String ACCESS_TOKEN_SECRET = "aNdRgUkXp2r5u8x/A?D(G+KbPeShVmYq";
    	 return Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes(StandardCharsets.UTF_8));
     }
     
     public static String createToken(String correoEelectronico)
     {
    	 long	TIME_CURRENT_SYSTEM = System.currentTimeMillis(); 
         long	TIME_MILLISECOND	= 900000; // equivalent: 15 minutes
         long	DUPLICATE_TIME 		= 1; // example: 900000 * 4 -> 1 hour; 
         
    	 try {
             String jws = Jwts.builder()
                 .signWith(generateKey(), SignatureAlgorithm.HS256)
                 .setSubject(correoEelectronico)
                 .setExpiration(new Date(TIME_CURRENT_SYSTEM + (TIME_MILLISECOND*DUPLICATE_TIME)))
                 .setIssuedAt(new Date(TIME_CURRENT_SYSTEM))
                 .compact();
             return jws;
         } catch (JwtException e) {
            System.out.println("ERROR EN: createToken() " + e.getMessage());
            System.out.println(e.getStackTrace()); 
            return "";
         }
     }
     
     public static UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken(String strSessionId)
     {
    	try
    	{
    		 Claims claims = Jwts.parserBuilder().setSigningKey(generateKey()).build().parseClaimsJws(strSessionId).getBody();
        	 String correoElectronico = claims.getSubject();
        	 return new UsernamePasswordAuthenticationToken(correoElectronico, null, Collections.emptyList());
        }
    	catch (Exception e) {
			System.out.println("ERROR EN: UsernamePasswordAuthenticationToken() " + e.getMessage());
			return null;
		}
     }
     
     public String getCorreoElectronicoToken(String strSessionId)
     { 
    	 try
    	 {
    		 Claims claims = Jwts.parserBuilder().setSigningKey(generateKey()).build().parseClaimsJws(strSessionId).getBody();
        	 String correoElectronico = claims.getSubject();
        	 return correoElectronico;
    	 }
    	 catch (ExpiredJwtException e) {
			return "-1";
		}
     }
	
}










