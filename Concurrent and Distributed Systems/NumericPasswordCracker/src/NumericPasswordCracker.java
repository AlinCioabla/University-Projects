import java.util.ArrayList;

public class NumericPasswordCracker implements PasswordCracker {

	NumericPasswordCracker(String aEncodedHash, int aNumberOfThreads, Cryptographer aCryptographer, int aLowerBound, int aUpperBound)
	{
		mNumberOfThreads = aNumberOfThreads;
		mEncodedHash = aEncodedHash;
		mCryptographer = aCryptographer;
		mThreadList = new ArrayList<Thread>(aNumberOfThreads);
		mLowerBound = aLowerBound;
		mUpperBound = aUpperBound;
		mNotifier = new PasswordFoundNotifier();
	}
	
	@Override
	public String Crack()
	{	
		if(mNumberOfThreads == 0)
			return null;
		
		SetThreads();
		StartThreads();

		for(Thread t : mThreadList)
		{

			try {
				t.join();
	
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(mNotifier.HasFound())
		{
			return mOutput = mNotifier.GetOutput();	
	    }
	
		return mOutput;
	}
	
	private void StartThreads()
    {
		for(Thread t : mThreadList)
		{
			t.start();
		}
    }
	
	
	
	
	private void SetThreads()
	{
		
		int currentBound = mLowerBound;
		int intervalSize = (int)((mUpperBound - mLowerBound) / mNumberOfThreads);
			
		for(int i=0; i < mNumberOfThreads - 1; i++)
		{
			NumbersBruteForceMatcher matcher = 
					new NumbersBruteForceMatcher(currentBound, currentBound + intervalSize,mCryptographer,mEncodedHash, mNotifier, Integer.toString(i+1));
			Thread thread = new Thread(matcher);
			mThreadList.add(thread);
			currentBound += intervalSize;	
		}
		
		NumbersBruteForceMatcher matcher = 
				new NumbersBruteForceMatcher(currentBound, currentBound + intervalSize + (mUpperBound - currentBound - intervalSize),mCryptographer,mEncodedHash, mNotifier, Integer.toString(mNumberOfThreads) );
		Thread thread = new Thread(matcher);
		mThreadList.add(thread);
	}
	
	@Override
	public String GetOutput() {
		return mOutput;
	}
	
	public String GetThreadName()
	{
		return mNotifier.GetFinderName();
	}
	
	private int mNumberOfThreads;
	private String mEncodedHash;
	private Cryptographer mCryptographer;
	private ArrayList<Thread> mThreadList;
	private int mLowerBound;
	private int mUpperBound;
	private PasswordFoundNotifier mNotifier;
	private String mOutput;

	



}
