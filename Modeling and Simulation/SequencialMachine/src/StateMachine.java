import java.util.HashMap;

public class StateMachine {

	public StateMachine(HashMap<Integer, HashMap<Integer, Integer>> aTable) {
		mTable = aTable;
	}

	public void TransitionTo(int aAction)
	{
		Integer nextState = mTable.get(mCurrentState).get(aAction);
		if(nextState != null)
		{
			mCurrentState = nextState;
		}
	}
	
	
	protected HashMap<Integer,HashMap<Integer, Integer>> mTable;
	protected int mCurrentState;
}
