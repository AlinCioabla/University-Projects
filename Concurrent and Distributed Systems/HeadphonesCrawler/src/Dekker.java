import java.util.concurrent.atomic.AtomicIntegerArray;
 
class Dekker {
  public Dekker (int aNumberThreads) 
  {
	mNumberThreads = aNumberThreads;
	mFlags = new AtomicIntegerArray(aNumberThreads);
	for(int i = 0; i < mNumberThreads; i++)
	{
		mFlags.set(i, 0);
	}
	  
	mTurn = 0;
  }
 
  public void Unlock(int t) {
    int other;
 
    other = mNumberThreads-1-t;
    mFlags.set(t,1);
    while (mFlags.get(other) == 1) {
      if (mTurn == other) {
        mFlags.set(t,0);
        while (mTurn == other)
          ;
        mFlags.set(t,1);
      }
    }
  }
 
  public void Lock(int t) {
	mTurn = mNumberThreads-1-t;
    mFlags.set(t,0);
  }
 
  private volatile int mTurn;
  private AtomicIntegerArray mFlags;
  private int mNumberThreads;
}