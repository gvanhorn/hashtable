public class Chain{
	Link head;
	int length;
	
	Chain(){
		head = null;
		length = 0;
	}
	
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