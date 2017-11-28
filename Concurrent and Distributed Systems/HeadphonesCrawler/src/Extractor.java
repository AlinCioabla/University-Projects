import java.util.ArrayList;

import org.jsoup.nodes.Document;

public abstract class Extractor {
	
	public Extractor(Document aDoc, ProductList aProducts,  Dekker aDek, int aID)
	{
		mDoc = aDoc;
		mProducts = aProducts;
		mID = aID;
		mDek = aDek;
	}
	
	public abstract void Run();
	
	
	protected Document mDoc;
	protected ProductList mProducts;
	protected int mID;
	protected Dekker mDek;
}
