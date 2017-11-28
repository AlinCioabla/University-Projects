import java.util.LinkedList;


public class Transition {
	public Transition(String aTag, LinkedList<Arc> aArce)
	{
		mTag = aTag;
		mArce = aArce;
	}
	
	public boolean IsValid()
	{
		for(Arc arc : mArce)
		{
			if(!arc.IsValid())
			{
				return false;
			}
		}
		return true;
	}
	
	public void Update()
	{
		for(Arc arc : mArce)
		{
			arc.Update();
		}
	}
	
	public String GetTag()
	{
		return mTag;
	}
	
	public LinkedList<Location> GetLocations()
	{
		LinkedList<Location> locs = new LinkedList<Location>();
		for(Arc arc:mArce)
		{
			locs.add(arc.GetLocatie());
		}
		return locs;
	}
	
	private LinkedList<Arc> mArce;
	private String mTag;
}
