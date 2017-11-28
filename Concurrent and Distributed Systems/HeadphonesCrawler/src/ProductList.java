import java.util.ArrayList;

public class ProductList {

	public ProductList()
	{
		mProducts = new ArrayList<Product>();
	}
	
	public synchronized void  Add(Product p)
	{
		int pos = AlreadyExists(p);
		if(pos != -1)
		{
			mProducts.get(pos).AddPrice(p.GetFirstPrice());
		}
		else
		{
			mProducts.add(p);
		}
	}
	
	private synchronized int AlreadyExists(Product p)
	{
		int pos = 0;
		for(Product current : mProducts)
		{
			if(current.GetID() == p.GetID())
			{
				return pos;
			}
			pos++;
		}
		return -1;
	}
	
	public void PrintLowestPrices() {
		for(Product p : mProducts)
		{
			Price lowestPrice = p.GetLowestPrice();
			System.out.println(p.GetTitle());
			System.out.println(p.GetID());
			System.out.println(lowestPrice.GetValue());
			System.out.println(lowestPrice.GetLink());
			System.out.println();
		}
		
	}
	
	public void Print() {
		for(Product p : mProducts)
		{	
			System.out.println(p.GetTitle());
			System.out.println(p.GetID());
			ArrayList<Price> prices = p.GetPrices();
			for(Price cp : prices)
			{
				System.out.println(cp.GetValue());
				System.out.println(cp.GetLink());
			}

			System.out.println();
		}
		
	}
	
	private ArrayList<Product> mProducts;

}
