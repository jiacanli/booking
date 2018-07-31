/**   
 * @Package: com.alpha.booking.util 
 * @author: jiacanli 
 * @date: 2018年7月31日 下午5:10:09 
 */
package com.alpha.booking.util;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/** 
 * @ClassName: AuthenticateUtil 
 * @Description: TODO
 * @author: jiacanli
 * @date: 2018年7月31日 下午5:10:09  
 */
public class AuthenticateUtil {
	
	private static final String PRIVATE_KEY_BASE64 = "MIIBUwIBADANBgkqhkiG9w0BAQEFAASCAT0wggE5AgEAAkEApdFMgUlGL3DfTOl7nokmzAmo52cA\r\n" + 
			"0zM6sttqGWoGgVBHYpafLikfJ34rWAIcn0WQEjJPy6rgMp1tWqRANXqx2QIDAQABAkBUxQs3jR40\r\n" + 
			"Yp/F0hdAlR0gck/G/QGO90Gq7kDvfSSWNn0aiOaLlaXChMl7Sd9rOZmjMVKU/THHkRrLewrBkE4B\r\n" + 
			"AiEA/VlVXdUC+krEMHwvqisYKMv9KKKL73ewDC+NwCe+mqUCIQCnjXzoMNWRe9IYMvasuuS/Ullr\r\n" + 
			"Fe2+FKPDuy5Kxt14JQIgNSlhuGbSUSMPN/1XIyYX07e6T6SMKJu83VG9NO0iGeECIFIpbovJjqDF\r\n" + 
			"sBOPwiiXiR5Mem6plSh1pIyRmDSw6d9FAiAoeF1ASGi9RVJ5h4OHcSqOXnf+VDebMU4bqy7ntASC\r\n" + 
			"Zw==";
	
	private static final String PUBLIC_KEY_BASE64 = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKXRTIFJRi9w30zpe56JJswJqOdnANMzOrLbahlqBoFQ\r\n" + 
			"R2KWny4pHyd+K1gCHJ9FkBIyT8uq4DKdbVqkQDV6sdkCAwEAAQ==";
	private static final int CHAR_LENGTH = 5;
	
	private static final String ALGORITHM = "RSA";
	
	
	private static PrivateKey PRIVATE_KEY ;
	
	static {
		PRIVATE_KEY = getPrivateKey(PRIVATE_KEY_BASE64);
	}

	/**
	 * 
	 */
	public AuthenticateUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public static boolean checkPermission(String token) {
		if(PRIVATE_KEY == null) {
			PRIVATE_KEY = getPrivateKey(PRIVATE_KEY_BASE64);
		}
				
		try {
			byte[] bytes = (new BASE64Decoder()).decodeBuffer(token);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, PRIVATE_KEY);
			byte[] clear = cipher.doFinal(bytes);
			return verifyText(clear);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 
	
	}
	
	private static boolean verifyText(byte[] clear_text) {
		return (new String(clear_text)).length() == CHAR_LENGTH;
	}
	
	public static PrivateKey getPrivateKey(String key){
		try {
	          byte[] keyBytes;
	          keyBytes = (new BASE64Decoder()).decodeBuffer(key);
	          PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
	          KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	          PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
	          return privateKey;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

    }
	
	public static PublicKey getPublicKey(String key) {
		try {
	          byte[] keyBytes;
	          keyBytes = (new BASE64Decoder()).decodeBuffer(key);
	          X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
	          KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	          PublicKey privateKey = keyFactory.generatePublic(keySpec);
	          return privateKey;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static void main(String[] args) {
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(PUBLIC_KEY_BASE64));
			byte[] plain = cipher.doFinal("12345".getBytes());
			System.out.println((new BASE64Encoder()).encode(plain));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
}
