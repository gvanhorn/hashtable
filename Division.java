/*
 * Gijs van Horn 10070370
 * Jeroen Vranken 10658491
 * Assignment 2
 */
public class Division implements Compressable {
    int table_length;
    int initial = 11;
    int multiplier = 31;
    
    Division(int length) {
        table_length = length;
    }

    public int calcIndex(String key) {
        int index;

        index = Math.abs(hashCode(key)) % table_length;
        return index;
    }

    int hashCode(String key) {
        int h = initial;
        char[] val = key.toCharArray();
        int len = key.length();
   
        for (int i = 0; i < len; i++) {
            h = multiplier * h + val[i];
        }
        return h;
    }

	//updateHashSize was added to implement a resize option for the linearprobe table.
	public void updateHashSize(int size) {
		table_length = size;
	}



}
