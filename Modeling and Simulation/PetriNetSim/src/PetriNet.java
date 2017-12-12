import java.util.LinkedList;


public class PetriNet {
	public PetriNet()
	{
		mTransitions = new LinkedList<Transition>();
		mValidTransitions = new LinkedList<Transition>();
	}
	
	
	public void Exec(String aAction)
	{
		mValidTransitions.clear();
		for(Transition trans:mTransitions)
		{
			if(trans.GetTag() == aAction && trans.IsValid())
			{
				mValidTransitions.add(trans);
			}
		}
		
		for(Transition trans : mValidTransitions) {
			trans.Update();
		}
	}
	
	
	protected LinkedList<Transition> mTransitions;
	private LinkedList<Transition> mValidTransitions;
}
