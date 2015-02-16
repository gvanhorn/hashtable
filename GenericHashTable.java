/*
 * Gijs van Horn 10070370
 * Jeroen Vranken 10658491
 * Assignment 2
 */

public interface GenericHashTable {
	void put(String key, String value);
	String get(String key);
	int getWordCount();
	int getHashSize();
	int getResizeCount();
}
