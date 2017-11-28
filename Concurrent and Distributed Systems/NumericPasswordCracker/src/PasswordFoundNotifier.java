
public class PasswordFoundNotifier {
	
	public PasswordFoundNotifier()
	{
		mPassword = "";
		mFoundPassword = false;
	}
	
	public void FindPassword(String aPassword, String aFinderName)
	{
		mPassword = aPassword;
		mFoundPassword = true;
		mFinderName = aFinderName;
	}
	
	public boolean HasFound()
	{
		return mFoundPassword;
	}
	
	public String GetOutput()
	{
		return mPassword;
	}
	
	public String GetFinderName()
	{
		return mFinderName;
	}
	
	private String mPassword;
	private boolean mFoundPassword;
	private String mFinderName;
}
