package dynamicUtil;

import java.util.*;

public class OTPGenerator {
	public static String generatePaasword() {
		String ret = null;
		int len=8; 
  
        String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
        String Small_chars = "abcdefghijklmnopqrstuvwxyz"; 
        String numbers = "0123456789"; 
                String symbols = "!@#$%^&*_=+-/.?<>)"; 
  
  
        String values = Capital_chars + Small_chars + 
                        numbers + symbols;  
        Random rndm_method = new Random(); 
  
        char[] password = new char[len]; 
  
        for (int i = 0; i < len; i++) 
        { 
             password[i] = 
              values.charAt(rndm_method.nextInt(values.length())); 
  
        } 
        ret = password.toString();
		return ret;
	}
}
