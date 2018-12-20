package com.olsa.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;

import org.apache.log4j.Logger;

public class GenreateUniqueID {
	private static final Logger logger = Logger.getLogger(GenreateUniqueID.class);
	public static void main(String[] args) {
		      // TODO Auto-generated method stub
		
		try {
		      //Initialize SecureRandom
		      //This is a lengthy operation, to be done only upon
		      //initialization of the application
		      SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
		      //generate a random number
		      String randomNum = new Integer(prng.nextInt(900000)+100000).toString();
		      //String randomNum = new Integer(prng.nextInt()).toString();
		      Date curDate = new Date();
		     
		      //myInt = sr.nextInt(900000) + 100000;
		      /*private static final Random generator = new Random();
		      public static int int generatePin() {
		          return 100000 + generator.nextInt(900000);
		      }*/
		      
		      //get its digest
		      MessageDigest sha = MessageDigest.getInstance("SHA-1");
		      byte[] result =  sha.digest(randomNum.getBytes());

		      logger.info("Random number: " + randomNum);
		      logger.info("Message digest: " + hexEncode(result));
		    }
		
		    catch (NoSuchAlgorithmException ex) {
		    	logger.error(ex);
		    }
	}
	
	 /**
	  * The byte[] returned by MessageDigest does not have a nice
	  * textual representation, so some form of encoding is usually performed.
	  *
	  * This implementation follows the example of David Flanagan's book
	  * "Java In A Nutshell", and converts a byte array into a String
	  * of hex characters.
	  *
	  * Another popular alternative is to use a "Base64" encoding.
	  */
	  static private String hexEncode(byte[] aInput){
	    StringBuilder result = new StringBuilder();
	    char[] digits = {'0', '1', '2', '3', '4','5','6','7','8','9','a','b','c','d','e','f'};
	    for (int idx = 0; idx < aInput.length; ++idx) {
	      byte b = aInput[idx];
	      result.append(digits[ (b&0xf0) >> 4 ]);
	      result.append(digits[ b&0x0f]);
	    }
	    return result.toString();
	  }
	} 



