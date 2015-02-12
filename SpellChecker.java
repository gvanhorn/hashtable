import java.util.*;
import java.io.*;
class SpellChecker {
	
	
    public static void main(String[] args) {
        int init_hash_size;
        int count = 0, typo = 0;
        long start = 0, end = 0;
        String wordfile, textfile;
        //Hashtable<String, String> table;
//        CollisionChainTable table;
        LinearProbeTable table;
        /* Shared token to store for every word in the hash table. */
        String placeholder = "a";

        if (!(args.length == 3) ) {
            System.out.println("Usage: java SpellChecker <wordfile> <text> <size>");
            System.exit(0);
        }
        wordfile = args[0];
        textfile = args[1];
        init_hash_size = Integer.parseInt(args[2]);
        System.out.printf("Selected table size: %d\n", init_hash_size);
        //table = new Hashtable<String, String>(hash_size);
        
        Compressable function = new Division(init_hash_size);
        table = new LinearProbeTable(init_hash_size, function);
        
        /* Read wordfile, and insert every word into the hash table. */
        try {
            BufferedReader in = new BufferedReader(new FileReader(wordfile));
            start = System.currentTimeMillis();
            String str, copy;
            while ((str = in.readLine()) != null) {
                copy = str.toLowerCase();
                table.put(copy, placeholder);
            }
            //System.out.println(table.toString());
            end = System.currentTimeMillis();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("java opbouwen Hashtable in " + (end - start) + " ms");

        // Read text file, and lookup every word in the hash table.
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

        System.out.printf("Hash table contains %d words\n", table.wordCount);
        System.out.printf("Hash table load factor %f\n",
               (double)table.getWordCount()/table.getHashSize());

        System.out.printf("Text contains %d words\n", count);
        System.out.printf("typo's %d\n", typo);

        System.out.println("zoeken woorden in " + (end - start) + " ms");
    }
    /* Checks if word contains digits. So it can be ignored for spell
     * checking. */
    static boolean contains_numbers(String str) {
        for (int i = 0 ; i < str.length() ; i++) 
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') 
                return true;

        return false;
    }
    
//    public Compressable makeCompressable(String compressType, int hashSize){
//    	Compressable function;
//    	
//    	switch(compressType){
//    		case "Division":
//    			function = new Division(hashSize);
//    			break;
//    		default:
//    			System.out.println("Unkown compression type");
//    	}
//    	
//    	return function;
//    }
    
    
    
    
    
    
    
    
    
    
}
