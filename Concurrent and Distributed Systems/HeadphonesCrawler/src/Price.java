

public class Price {
	
	public Price(float aVal, String aLink)
	{
		mValue = aVal;
		mLink = aLink;
	}
	
	public Price()
	{
		mValue = 0;
		mLink = "";
	}
	
	public float GetValue()
	{
		return mValue;
	}
	
	public String GetLink()
	{
		return mLink;
	}
	
	public void SetPriceValue(float aVal)
	{
		mValue = aVal;
	}
	
	public void SetLink(String aLink)
	{
		mLink = aLink;
	}
	
	protected String mLink;
	protected float mValue;
}
