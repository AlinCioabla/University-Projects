
public class NumbersBruteForceMatcher implements Runnable{
	
	
	public NumbersBruteForceMatcher(int aLowerLimit, int aUpperLimit, Cryptographer aCryptographer, String aEncodedHash, PasswordFoundNotifier aNotifier, String aName)
	{
		mLowerLimit = aLowerLimit;
		mUpperLimit = aUpperLimit;
		mCryptographer = aCryptographer;
		mEncodedHash = aEncodedHash;
		mNotifier = aNotifier;
		mName = aName;
	}
	
	@Override
	public void run() {
		System.out.println("Starting Thread" + mName + "on interval " + mLowerLimit + "-" +mUpperLimit);
		for(int i = mLowerLimit; i <= mUpperLimit;i++)
		{
			if(mNotifier.HasFound())
			{
				return;
			}
			String currentEncodedValue = mCryptographer.Crypt(Integer.toString(i));
			if(currentEncodedValue.equals(mEncodedHash))
			{
				String output = Integer.toString(i);
				mNotifier.FindPassword(output, mName);
			}
		}
		
	}
	
	
	private int mLowerLimit,mUpperLimit;
	private Cryptographer mCryptographer;
	private String mEncodedHash;
	private PasswordFoundNotifier mNotifier;
	private String mName;
}
