package com.mercadolibre.app.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.mercadolibre.app.enums.ErrorEnum;
import com.mercadolibre.app.exceptions.ApiException;

/**
 * Utilidad para la generación del checksum
 * @author Jesus Matiz <jesusmatiz35@gmail.com>
 *
 * @date 14/04/2021 7:39:59 a. m.
 */
public class CustomMD5 {
	
	public static String getMd5(String input)
    {
        try {
  
            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
  
            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());
  
            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);
  
            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } 
  
        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
        	throw new ApiException(ErrorEnum.MD5_ERROR);
        }
    }

}
