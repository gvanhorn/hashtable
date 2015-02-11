import java.util.ArrayList;
import java.util.LinkedList;


public class CollisionChainTable {
	//size is the amount of LinkedLists
	int size;
	//wordCount is the total amount of words in the table.
	int wordCount;
	
	ArrayList<LinkedList<Entry>> table;
	Compressable function;
	
	//CollisionHashtable constructor, initializes all fields.
	CollisionChainTable(int hashSize, Compressable hashFunction){
		size = hashSize;
		function = hashFunction;
		wordCount = 0;
		table = new ArrayList<LinkedList<Entry>>();
		
		//Created hashSize amount of empty LinkedLists and puts them in the table.
		for(int i=0; i<hashSize; i++){
			LinkedList<Entry> emptylist = new LinkedList<Entry>();
			table.add(emptylist);
		}	
	}
	
	//Creates an entry for key value pair and puts it in the linkedList in the table
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
		
		//loop over linkedlist and find the value corresponding to the key
		for(int i = 0; i < linkedListSize; i++){
			if(key.equals(tempList.get(i).getKey())){
				value = tempList.get(i).getValue();	
			}
		}

		return value;
	}
	
	public int size(){
		return size;
	}	
}
