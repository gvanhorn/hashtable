
public class LinearProbeTable {
	int size;
	int wordCount;
	double threshold;
	Compressable function;
	Entry[] table;
	
	LinearProbeTable(int hashSize, Compressable hashFunction){
		size = hashSize;
		function = hashFunction;
		wordCount = 0;
		table = new Entry[size];
		threshold = 0.75;
	}
	
	public void put(String key, String value){
		if(wordCount++ / size > threshold){
			resize();
		}
		
		
		Entry newEntry = new Entry(key, value);
		int index = function.calcIndex(key);
		
		if(table[index]==null){
			table[index] = newEntry;
		}else{
			index = index++ % size;
			while(table[index]!=null){
				index++;
			}
			table[index]=newEntry;
		}
		wordCount++;
		
	}
	
	public String get(String key){
		int index = function.calcIndex(key);
		
		if (table[index]== null){
			return null;
		}
		
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
	
	public void resize(){
		int newSize = 2*size;
		Entry[] newTable = new Entry[newSize];
		for(Entry entry : table){
			
		}
	}
	
	public int size(){
		return size;
	}
	
	public int getWordCount(){
		return wordCount;
	}

}
