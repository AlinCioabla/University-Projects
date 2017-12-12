
public class Location {
	public Location(String aTag) {
		mJetoane = 0;
		mTag = aTag;
	}

	public Location(String aTag, int aJetoane) {
		mTag = aTag;
		mJetoane = aJetoane;
	}

	public boolean UpdateJetoane(int aValue) {
		int result = mJetoane + aValue;
		if (result < 0) {
			return false;
		}
		mJetoane = result;
		return true;
	}

	public int GetJetoane() {
		return mJetoane;
	}

	public String GetTag() {
		return mTag;
	}

	private int mJetoane;
	private String mTag;
}
