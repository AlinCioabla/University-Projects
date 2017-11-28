import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHACryptographer implements Cryptographer{

	public SHACryptographer(String aCryptType)
	{
		mCryptType = aCryptType;
		mUppercaseHash = true;
	}
	
	public void SetUppercase(boolean aMode)
	{
		mUppercaseHash = aMode;
	}
	
	public String GetCryptType()
	{
		return mCryptType;
	}
	
	public String Crypt(String aValue)
	{
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance(mCryptType);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] encodedHash = digest.digest(
		  aValue.getBytes(StandardCharsets.UTF_8));
		

		return bytesToHex(encodedHash);
	}
	
	
	private String bytesToHex(byte[] hash) {
	    StringBuffer hexString = new StringBuffer();
	    for (int i = 0; i < hash.length; i++) {
	    String hex = Integer.toHexString(0xff & hash[i]);
	    if(hex.length() == 1) hexString.append('0');
	        hexString.append(hex);
	    }
	    if(mUppercaseHash == true)
	    {
	    	return hexString.toString().toUpperCase();
	    }
	    
	    
	    return hexString.toString();
	    
	}
	
	private boolean mUppercaseHash;
	private String mCryptType;
	
}
