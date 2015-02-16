/*Class chain is our implementation of a linked list,  
 * A chain has a head (which is a link) and a length. 
 */


public class Chain{
	Link head;
	int length;
	
	Chain(){
		head = null;
		length = 0;
	}
	
	/*
	 * Finds the last link in the chain and appends the provided link.
	 */
	void addLink(Entry entry){
		if(length==0){
			head = new Link(entry);
			length++;
		}else{
			Link newLink = new Link(entry);
			Link nextLink = head;
			while(nextLink.getNext() != null){
				nextLink = nextLink.getNext();
			}
			nextLink.setNextLink(newLink);
			length++;
		}
	}
	
	public int getLength(){
		return length;
	}
	
	/*
	 * Iterates over the chain and finds the entry matching with the provided key and returns the entry.
	 */
	public Entry findEntry(String key){
		if(length == 0){
			return null;
		}
		Link currentLink = head;
		
		while(!currentLink.getEntry().getKey().equals(key)){
			if(currentLink.getNext() != null){
				currentLink = currentLink.getNext();
			}else{
				return null;
			}
		}
		
		return currentLink.getEntry();
		
	}

	/*
	 * A link is an entry and a reference to the next link in the chain, null if it is the last link in the chain.
	 */
	private class Link{
		Entry data;
		Link nextLink;
		
		Link(Entry entry){
			data = entry;
			nextLink = null;
		}
		
		public Entry getEntry(){
			return data;
		}
		
		public Link getNext(){
			return nextLink;
		}
		
		public void setNextLink(Link next){
			nextLink = next;
		}
	}
	
	
}