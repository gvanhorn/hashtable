import java.util.*;
import java.io.*;
class SpellChecker {
	
	
    public static void main(String[] args) {
        int init_hash_size;
        String wordfile, textfile;
        
        //parse inputarguments
        if (!(args.length == 3) ) {
            System.out.println("Usage: java SpellChecker <wordfile> <text> <size>");
            System.exit(0);
        }
        wordfile = args[0];
        textfile = args[1];
        init_hash_size = Integer.parseInt(args[2]);
        System.out.printf("Selected table size: %d\n", init_hash_size);
        
        int hashSize = init_hash_size;
        long start =0, end = 0;
        double loadFactor = 0;
        //Time the LinearProbeTable 10 times, doubling the initial hash_size each time.
        try {
        	File resultFile = new File("linearProbing_" + init_hash_size);
        	if(!resultFile.exists()){
				resultFile.createNewFile();
			} 
        	FileWriter fw = new FileWriter(resultFile.getAbsoluteFile());
        	BufferedWriter bw = new BufferedWriter(fw);
        	bw.write("HashSize, fillTime, checkTime, loadFactor, resizeCount \n");
        	
	        for(int i = 1; i<= 10; i++){
	        	System.out.println(i);
	        	
		        //Create linearProbeTable and fill it.
		        GenericHashTable table;
		        Compressable function = new Division(hashSize);
		        table = new LinearProbeTable(hashSize, function);
		        
		        start = System.currentTimeMillis();
		        table = fillHashTable(wordfile, table);
		        end = System.currentTimeMillis();
		        long fillTime = end-start;
		        
		        // Read text file, and lookup every word in the hash table.
		        long[] results = checkForErrors(textfile, table);
		        loadFactor = table.getWordCount()/table.getHashSize();
		        
		        System.out.println("java opbouwen Hashtable in " + fillTime + " ms");
		        bw.write(hashSize + ", " + fillTime + ", " + results[2] +", " + loadFactor + ", " + table.getResizeCount() +  "\n");
		        hashSize *= 2;
	        }
	        bw.close();
        }
        catch (IOException e) {
			e.printStackTrace();
		}
        
        
        
        try {
        	File resultFile = new File("collisionChaining" + init_hash_size);
        	if(!resultFile.exists()){
				resultFile.createNewFile();
			} 
        	FileWriter fw = new FileWriter(resultFile.getAbsoluteFile());
        	BufferedWriter bw = new BufferedWriter(fw);
        	bw.write("HashSize, fillTime, checkTime, loadFactor, resizeCount\n");
        	
	        for(int i = 1; i<= 10; i++){
	        	System.out.println(i);
	        	
		        //Create linearProbeTable and fill it.
		        GenericHashTable table;
		        Compressable function = new Division(hashSize);
		        table = new CollisionChainTable(hashSize, function);
		        
		        start = System.currentTimeMillis();
		        table = fillHashTable(wordfile, table);
		        end = System.currentTimeMillis();
		        long fillTime = end-start;
		        
		        // Read text file, and lookup every word in the hash table.
		        long[] results = checkForErrors(textfile, table);
		        loadFactor = table.getWordCount()/table.getHashSize();
		        
		        System.out.println("java opbouwen Hashtable in " + fillTime + " ms");
		        bw.write(hashSize + ", " + fillTime + ", " + results[2] +", " + loadFactor + ", " + table.getResizeCount() +  "\n");
		        hashSize *= 2;
	        }
	        bw.close();
        }
        catch (IOException e) {
			e.printStackTrace();
		}
        
        
//        System.out.printf("Hash table contains %d words\n", table.getWordCount());
//        System.out.printf("Hash table load factor %f\n",
//               (double)table.getWordCount()/table.getHashSize());
//
//        System.out.printf("Text contains %d words\n", results[1]);
//        System.out.printf("typo's %d\n", results[0]);
//
//        System.out.println("zoeken woorden in " + results[2] + " ms");
    }
    /* Checks if word contains digits. So it can be ignored for spell
     * checking. */
    static boolean contains_numbers(String str) {
        for (int i = 0 ; i < str.length() ; i++) 
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') 
                return true;

        return false;
    }
    
    public static GenericHashTable fillHashTable(String wordfile, GenericHashTable table){
    	String placeholder = "a";
    	
    	
	    try {
	        BufferedReader in = new BufferedReader(new FileReader(wordfile));
	        
	        String str, copy;
	        while ((str = in.readLine()) != null) {
	            copy = str.toLowerCase();
	            table.put(copy, placeholder);
	        }
	        //System.out.println(table.toString());
	        
	        in.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    return table;
    }
    
    public static long[] checkForErrors(String textfile, GenericHashTable table){
    	long start = 0, end = 0;
    	long typo = 0, count = 0;
        try {
            BufferedReader src = new BufferedReader(new FileReader(textfile));
            start = System.currentTimeMillis();
            String str = src.readLine();
            while (str != null) {
                String copy = str.toLowerCase();

                StringTokenizer st = new StringTokenizer(copy, " ,.:;\"-_(){}[]?!*^&'\n\t");
                while(st.hasMoreTokens()) {
                    String word = st.nextToken();
                    if (!contains_numbers(word) && table.get(word) == null) {
                        //System.out.printf("Not found: [%s]\n", word);
                        typo++;
                    }
                    count++;
                }
                str = src.readLine();
            }
            src.close();
            end = System.currentTimeMillis();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long runningTime = end-start;
        
        long[] returnValue = new long[3];
        returnValue[0] = typo;
        returnValue[1] = count;
        returnValue[2] = runningTime;
        return returnValue;

    }
    
    
}
