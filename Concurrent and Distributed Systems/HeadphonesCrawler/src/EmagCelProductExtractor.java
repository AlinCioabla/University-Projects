import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class EmagCelProductExtractor implements Runnable{

	public EmagCelProductExtractor(String aCelUrl, String aEmagUrl, ProductList aProducts, Dekker aDekker, int aThreadID) throws IOException
	{
		mEmagDoc = Jsoup.connect(aEmagUrl).get();
		mCelDoc = Jsoup.connect(aCelUrl).get();
		mProducts = aProducts;
		mDekker = aDekker;
		mThreadID = aThreadID;
	}
	
	
	@Override
	public void run() {
		if(mCelDoc != null)
			HandleCel();
		
		if(mEmagDoc != null)
			HandleEmag();
	}
	
	
	private void HandleCel()
	{
		CelExtractor celEx = new CelExtractor(mCelDoc, mProducts, mDekker,mThreadID);
		celEx.Run();
	}
	
	private void HandleEmag()
	{
		EmagExtractor emagEx = new EmagExtractor(mEmagDoc, mProducts, mDekker,mThreadID);
		emagEx.Run();
	}
	
	private Document mEmagDoc;
	private Document mCelDoc;
	private ProductList mProducts;
	private Dekker mDekker;
	private int mThreadID;

}
