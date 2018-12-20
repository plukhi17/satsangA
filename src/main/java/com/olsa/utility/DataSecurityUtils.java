package com.olsa.utility;
import org.apache.log4j.Logger;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class DataSecurityUtils {
	private static final Logger logger = Logger.getLogger(DataSecurityUtils.class);
	 public static String makedHashData(String data) {
	        try {
	        	String salt="plmnkbHVlaW5mb2thdXNoYWw";
	            String saltedAndHashed = data + "," + salt;
	            MessageDigest digest = MessageDigest.getInstance("MD5");
	            digest.update(saltedAndHashed.getBytes());
	            BASE64Encoder encoder = new BASE64Encoder();
	            byte hashedBytes[] = (new String(digest.digest(), "UTF-8")).getBytes();
	            return encoder.encode(hashedBytes) + "," + salt;
	            
	        } catch (NoSuchAlgorithmException e) {
	        	logger.error("UTF-8 unavailable?  Not a chance", e);
	        	throw new RuntimeException("UTF-8 unavailable?  Not a chance\", e");
	        } catch (UnsupportedEncodingException e) {
	        	logger.error("UTF-8 unavailable?  Not a chance", e);
	        	throw new RuntimeException("UTF-8 unavailable?  Not a chance\", e");
	        }
	    }
}
