
public class LinearProbeTable implements GenericHashTable{
	int size;
	int wordCount;
	int resizeCount;
	double threshold;
	Compressable function;
	Entry[] table;
	
	LinearProbeTable(int hashSize, Compressable hashFunction){
		size = hashSize;
		function = hashFunction;
		resizeCount = 0;
		wordCount = 0;
		table = new Entry[size];
		threshold = 0.75;
	}
	
	public void put(String key, String value){
		if((wordCount++ / size ) > threshold){
			resize();
		}
		
		
		Entry newEntry = new Entry(key, value);
		int index = function.calcIndex(key);
		
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
		//System.out.println("Resizing");
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
