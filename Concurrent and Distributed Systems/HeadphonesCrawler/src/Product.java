import java.net.MalformedURLException;
import java.util.ArrayList;

public abstract class Product {
	protected String mTitle;
	protected int mID;
	protected ArrayList<Price> mPrices;
	
	
	public Product(String aTitle, int aID)
	{
		mTitle = aTitle;
		mID = aID;
		mPrices = new ArrayList<Price>();
	}
	
	public Product()
	{
		mTitle ="";
		mID = 0;
		mPrices = new ArrayList<Price>();
	}
	
	public void SetTitle(String aTitle)
	{
		mTitle = aTitle;
	}
	
	public void SetID(int aID)
	{
		mID = aID;
	}
	
	
	public void AddPrice(Price aPrice)
	{
		mPrices.add(aPrice);
	}
	
	public void AddPrice(float aVal, String aLink)
	{
		Price p = new Price(aVal,aLink);
		mPrices.add(p);
	}
	
	
	public String GetTitle()
	{
		return mTitle;
	}
	
	public int GetID()
	{
		return mID;
	}
	
	public Price GetFirstPrice()
	{
		if(mPrices.size() != 0)
			return mPrices.get(0);
		return null;
	}
	
	public ArrayList<Price> GetPrices()
	{
		return new ArrayList<Price>(mPrices);
	}
	
	public Price GetLowestPrice()
	{
		Price p = mPrices.get(0);
		for(Price current : mPrices)
		{
			if(current.GetValue() < p.GetValue())
			{
				p = current;
			}
		}
		return p;
	}
	
	
	
}
