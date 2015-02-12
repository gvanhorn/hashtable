
public class testMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Entry test = new Entry("key", "value");
		Entry test1 = new Entry("key1", "value1");
		Entry test2 = new Entry("key2", "value2");
		Entry test3 = new Entry("key3", "value3");
		Entry test4 = new Entry("key4", "value4");
		
		
		Chain mychain = new Chain();
		mychain.addLink(test);
		mychain.addLink(test1);
		mychain.addLink(test2);
		mychain.addLink(test3);
		mychain.addLink(test4);
		
		//System.out.println(mychain.findEntry("key3").getValue());
		

	}

}
