/**   
 * @Package: com.alpha.booking.util 
 * @author: jiacanli 
 * @date: 2018年7月31日 下午5:10:09 
 */
package com.alpha.booking.util;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

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
	
	private static final String AES_KEY_STR = "Xjs+umBqro8Ne";
	
	private static final int CHAR_LENGTH = 5;
	
	private static final String ALGORITHM = "RSA";
	
	
	private static PrivateKey PRIVATE_KEY ;
	
	private static PublicKey PUBLIC_KEY;
	
	private static SecretKey AES_KEY ;
	
	static {
		PRIVATE_KEY = getPrivateKey(PRIVATE_KEY_BASE64);
		PUBLIC_KEY = getPublicKey(PUBLIC_KEY_BASE64);
		AES_KEY = getAESkey(AES_KEY_STR);
	}

	/**
	 * 
	 */
	public AuthenticateUtil() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	private static SecretKey getAESkey(String key_str) {
		try {
            //1.构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator keygen=KeyGenerator.getInstance("AES");
            //2.根据ecnodeRules规则初始化密钥生成器
            //生成一个128位的随机源,根据传入的字节数组
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );
            secureRandom.setSeed(key_str.getBytes());
            keygen.init(128, secureRandom);
              //3.产生原始对称密钥
            SecretKey original_key=keygen.generateKey();
              //4.获得原始对称密钥的字节数组
            byte [] raw=original_key.getEncoded();
//            System.err.println(raw.length);
//            System.err.println((new BASE64Encoder()).encode(raw));
            //5.根据字节数组生成AES密钥
            SecretKey key=new SecretKeySpec(raw, "AES");

            return key;
        } catch (Exception e) {
            e.printStackTrace();
        } 
        
        //如果有错就返加nulll
        return null;    
	}
	
	public static String decrypt(String plain_text) throws Exception {
		byte[] plain = (new BASE64Decoder()).decodeBuffer(plain_text);
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, PRIVATE_KEY);
		byte[] clear = cipher.doFinal(plain);
		return new String(clear);
	}
	
	public static String AESdecrypt(String plain_text) throws Exception {
		byte[] plain = (new BASE64Decoder()).decodeBuffer(plain_text);
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, AES_KEY);
		byte[] clear = cipher.doFinal(plain);
		return new String(clear);
	}
	

	
//	public static String encrypt(String clear_text) throws Exception {
//		byte[] clear = (new BASE64Decoder()).decodeBuffer(clear_text);
//		Cipher cipher = Cipher.getInstance("RSA");
//		cipher.init(Cipher.ENCRYPT_MODE, PUBLIC_KEY);
//		byte[] plain = cipher.doFinal(clear_text.getBytes("UTF-8"));
//		return (new BASE64Encoder()).encode(plain);
//	}
	
	public static String AESencrypt(String clear_text) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, AES_KEY);
		byte[] plain = cipher.doFinal(clear_text.getBytes("UTF-8"));
		return (new BASE64Encoder()).encode(plain);
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
			String base  = "cDqftW3LdptW9gwmR+RoAlB5nmw5mRTLI4+QDq0NZKWvnmwyJCzXsRVPiFt7m46DbjpnMMunCUA4\nEppjpfXKg79/geP2/LNB4qsj51HqB5I=";
//			System.out.println(AESencrypt("1235566666"));
			System.out.println(AESdecrypt(base));
//			System.out.println("hangge.com123456".getBytes().length);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
}
