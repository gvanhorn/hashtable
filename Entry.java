
//Class entry is a key-value pair object.
public class Entry{
		String key;
		String value;
		
		Entry(){
			key="";
			value="";
		}
		
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
		
		public void print(){
			System.out.println(key + " - " + value);
		}
		
		public boolean isEmpty(){
			return true;
		}
		
}
