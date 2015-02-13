
public interface GenericHashTable {
	void put(String key, String value);
	String get(String key);
	int getWordCount();
	int getHashSize();
	int getResizeCount();
}
