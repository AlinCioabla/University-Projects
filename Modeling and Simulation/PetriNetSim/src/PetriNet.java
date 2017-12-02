import java.util.LinkedList;


public class PetriNet {
	public PetriNet()
	{
		mTransitions = new LinkedList<Transition>();
	}
	
	
	public void Exec(String aAction)
	{
		for(Transition trans:mTransitions)
		{
			if(trans.GetTag() == aAction && trans.IsValid())
			{
				trans.Update();
				return;
			}
		}
	}
	
	
	protected LinkedList<Transition> mTransitions;
}
