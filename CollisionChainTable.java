
public class CollisionChainTable {
	int size;
	String[] table;
	Compressable function;
	
	CollisionChainTable(int hashSize, Compressable hashFunction){
		size = hashSize;
		function = hashFunction;
		table = new String[hashSize];
		
		
	}
	
	public void put(String key, String value){
		int index = function.calcIndex(key);
		table[index]= key;
		
	}
	
	public String get(String key){
		return key;
	}
	
	public int size(){
		return size;
	}
	
}
