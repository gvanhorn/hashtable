/*
 * Gijs van Horn 10070370
 * Jeroen Vranken 10658491
 * Assignment 2
 */
public class LinearProbeTable implements GenericHashTable{
	int size;
	int wordCount;
	int resizeCount;
	double threshold;
	Compressable function;
	Entry[] table;
	
	//Constructor for the linear probing table.
	LinearProbeTable(int hashSize, Compressable hashFunction){
		size = hashSize;
		function = hashFunction;
		resizeCount = 0;
		wordCount = 0;
		table = new Entry[size];
		threshold = 0.75;
	}
	
	
	public void put(String key, String value){
		//if the loadfactor is higher than the threshold, double size
		if(((double)wordCount++ / size ) > threshold){
			resize();
		}
		
		//Create entry to put
		Entry newEntry = new Entry(key, value);
		int index = function.calcIndex(key);
		
		//Find an empty place for the entry, and add it there.
		boolean wordadded = false;
		while(!wordadded){
			if(table[index]==null){
				table[index] = newEntry;
				wordadded = true;
				wordCount++;
			}else{
				index++;
				index = index % size;				
			}
		}	
		
	}
	
	//Get(key) returns the value associated to the key.
	public String get(String key){
		int index = function.calcIndex(key);
		
		//If the index is empty, the key is not present in the table
		if (table[index]== null){
			return null;
		}
		
		//Iterate over indexes comparing the key the entries, untill match or null value was found.
		else if (key.equals(table[index].getKey())){
			return table[index].getValue();
		}else{
			index = index++ % size;
			while(table[index]!= null){
				if(key.equals(table[index].getKey())){
					return table[index].getValue();
				}
				index++;
			}
			return null;
		}
	}
	
	/*Double the array size
	 * 
	 *first makes a temporary deep copy of the current table to oldTable
	 *creates a new table double the oldtables size
	 *iterates over oldTable and puts them in the new table with a new hashfunction based on the new size. 
	 */
	public void resize(){
		resizeCount++;
		Entry[] oldTable = new Entry[size];
		System.arraycopy(table, 0, oldTable, 0, size);
		size *=2;
		table = new Entry[size];
		function.updateHashSize(size);
		
		
		for(Entry e : oldTable){
			if(e != null){
				this.put(e.getKey(), e.getValue());
				//System.out.println(e.getKey());
			}
		}
		
		
	}
	
	public int getResizeCount(){
		return resizeCount;
	}
	
	public int getHashSize(){
		return size;
	}
	
	public int size(){
		return size;
	}
	
	public int getWordCount(){
		return wordCount;
	}

}
