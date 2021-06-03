import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class HashUtils {
	//SHA256 HASHING 
	public static String sha256Hash(String input) {
		String hashtext = null;
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(input.getBytes(StandardCharsets.UTF_8));
			byte [] digest = md.digest();
			
			hashtext = String.format("%064x", new BigInteger(1,digest));
		}
		catch(NoSuchAlgorithmException e)
		{
			throw new RuntimeException(e);
		}
		return hashtext ;
	}
	//SHA1 HASHING 
	public static String sha1Hash(String input) {
		String hashtext  = null;
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(input.getBytes(StandardCharsets.UTF_8));
			byte [] digest = md.digest();
			
			// Convert byte array into signum representation
            BigInteger no = new BigInteger(1, digest);
  
            // Convert message digest into hex value
            hashtext = no.toString(16);
			// Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
		}
		catch(NoSuchAlgorithmException e)
		{
			throw new RuntimeException(e);
		}
		return hashtext ;
	}

	public static String sha512Hash(String input) {
		String hashtext  = null;
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(input.getBytes(StandardCharsets.UTF_8));
			byte [] digest = md.digest();
			
			// Convert byte array into signum representation
            BigInteger no = new BigInteger(1, digest);
  
            // Convert message digest into hex value
            hashtext = no.toString(16);
			// Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
		}
		catch(NoSuchAlgorithmException e)
		{
			throw new RuntimeException(e);
		}
		return hashtext ;
	}
}
