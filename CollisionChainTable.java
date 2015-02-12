public class CollisionChainTable {
	//size is the amount of LinkedLists
	int size;
	//wordCount is the total amount of words in the table.
	int wordCount;
	
	Chain[] table;
	Compressable function;
	
	//CollisionHashtable constructor, initializes all fields.
	CollisionChainTable(int hashSize, Compressable hashFunction){
		size = hashSize;
		function = hashFunction;
		wordCount = 0;
		table = new Chain[hashSize];
		for(int i=0; i<hashSize; i++){
			table[i]= new Chain();
		}
		
	}
	
	//Creates an entry for key value pair and puts it in the linkedList in the table
	public void put(String key, String value){
		int index = function.calcIndex(key);
		Entry entry = new Entry(key,value);
		table[index].addLink(entry);
		wordCount++;
	}
	
	public String get(String key){
		//hash key to find index of linkedlist and find it
		int index = function.calcIndex(key);
		Chain tempList = table[index];
		Entry tempEntry = tempList.findEntry(key);
		if(tempEntry == null){
			return null;
		}else{
			return tempEntry.getValue();
		}
	}
	
	public int getHashSize(){
		return size;
	}

	public int getWordCount() {
		return wordCount;
	}
	

}



