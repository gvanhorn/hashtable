/*
 * Gijs van Horn 10070370
 * Jeroen Vranken 10658491
 * Assignment 2
 */

import java.util.*;
import java.io.*;
class SpellChecker {
	
	
    public static void main(String[] args) {
        int init_hash_size;
        String wordfile, textfile;
        
        //parse input arguments
        if (!(args.length == 3) ) {
            System.out.println("Usage: java SpellChecker <wordfile> <text> <size>");
            System.exit(0);
        }
        wordfile = args[0];
        textfile = args[1];
        init_hash_size = Integer.parseInt(args[2]);
        //System.out.printf("Selected table size: %d\n", init_hash_size);
        
        int hashSize = init_hash_size;
        long start =0, end = 0;
        double loadFactor = 0;
        
        
        try {
        	File resultFile = new File("linearProbing_" + init_hash_size);
        	if(!resultFile.exists()){
				resultFile.createNewFile();
			} 
        	FileWriter fw = new FileWriter(resultFile.getAbsoluteFile());
        	BufferedWriter bw = new BufferedWriter(fw);
        	bw.write("HashSize, fillTime, checkTime, loadFactor, resizeCount\n");
        	
	        for(int i = 1; i<= 20; i++){
	        	System.out.println("Currently testing LinearProbeTable with size: " + hashSize);
	        	
		        //Create CollisionChainTable and fill it.
		        GenericHashTable table;
		        Compressable function = new Division(hashSize);
		        table = new LinearProbeTable(hashSize, function);
		        
		        //Fill the table and time operation
		        start = System.currentTimeMillis();
		        table = fillHashTable(wordfile, table);
		        end = System.currentTimeMillis();
		        long fillTime = end-start;
		        System.out.println("built Hashtable in " + fillTime + " ms");
		        
		        // Read text file, and lookup every word in the hash table.
		        start = System.currentTimeMillis();
		        int[] results = checkForErrors(textfile, table);
		        end = System.currentTimeMillis();
		        long checkTime = end-start;
		        System.out.println("checked for errors in textfile in: " + checkTime + " ms, found " + results[0]+ " errors");
		        loadFactor = (double)table.getWordCount()/table.getHashSize();
		        
		        
		        
		        bw.write(hashSize + ", " + fillTime + ", " + checkTime +", " + loadFactor + ", " + table.getResizeCount() +  "\n");
		        hashSize = hashSize + 25000;
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
	        	System.out.println("Currently testing CollisionChainTable with size: " + hashSize);
	        	
		        //Create CollisionChainTable and fill it.
		        GenericHashTable table;
		        Compressable function = new Division(hashSize);
		        table = new CollisionChainTable(hashSize, function);
		        
		        //Fill the table and time operation
		        start = System.currentTimeMillis();
		        table = fillHashTable(wordfile, table);
		        end = System.currentTimeMillis();
		        long fillTime = end-start;
		        System.out.println("built Hashtable in " + fillTime + " ms");
		        
		        // Read text file, and lookup every word in the hash table.
		        start = System.currentTimeMillis();
		        int[] results = checkForErrors(textfile, table);
		        end = System.currentTimeMillis();
		        long checkTime = end-start;
		        System.out.println("checked for errors in textfile in: " + checkTime + " ms, found " + results[0]+ " errors");
		        loadFactor = (double)table.getWordCount()/table.getHashSize();
		        
		        
		        bw.write(hashSize + ", " + fillTime + ", " + checkTime +", " + loadFactor + ", " + table.getResizeCount() +  "\n");
		        hashSize *= 2;
	        }
	        bw.close();
        }
        catch (IOException e) {
			e.printStackTrace();
		}
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
    
    public static int[] checkForErrors(String textfile, GenericHashTable table){
    	
    	int typo = 0, count = 0;
        try {
            BufferedReader src = new BufferedReader(new FileReader(textfile));
            
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
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        int[] returnValue = new int[2];
        returnValue[0] = typo;
        returnValue[1] = count;
        
        return returnValue;

    }
    
    
}
