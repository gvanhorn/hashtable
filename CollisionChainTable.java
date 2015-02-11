import java.util.ArrayList;
import java.util.LinkedList;


public class CollisionChainTable {
	int size;
	int wordCount;
	ArrayList<LinkedList<Entry>> table;
	Compressable function;
	
	CollisionChainTable(int hashSize, Compressable hashFunction){
		size = hashSize;
		function = hashFunction;
		wordCount = 0;
		table = new ArrayList<LinkedList<Entry>>();
		
		for(int i=0; i<hashSize; i++){
			LinkedList<Entry> emptylist = new LinkedList<Entry>();
			table.add(emptylist);
		}
		
		//System.out.println(table.size());
		
		
	}
	
	@SuppressWarnings("unchecked")
	public void put(String key, String value){
		int index = function.calcIndex(key);
		Entry entry = new Entry(key,value);
		
		table.get(index).add(entry);
		
		wordCount++;
		
		
	}
	
	public String get(String key){
		//hash key to find index of linkedlist and find it
		int index = function.calcIndex(key);
		LinkedList<Entry> tempList = table.get(index);
		int linkedListSize = tempList.size();
		
		//default return value is null
		String value = null;
		
		
		//loop over linkedlist and find the key
		for(int i = 0; i < linkedListSize; i++){
			if(key.equals(tempList.get(i).getKey())){
				value = tempList.get(i).getValue();	
			}
		}
		
		//System.out.println(key +" "+ value);
		return value;
	}
	
	public int size(){
		return size;
	}
	
	class Entry{
		String key;
		String value;
		
		Entry(String inputkey, String inputvalue){
			key = inputkey;
			value = inputvalue;
		}
		
		public String getKey(){
			return key;
		}
		
		public String getValue(){
			return value;
		}
		
	}
	
}
