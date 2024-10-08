
public class LinkedList {

	private reading head = null;
	private reading tail = null;
	private reading current = null;

	public void add(reading element) {

		if (head == null) {
			head = element;
			current = head;
			tail=head;
			
		}
		
		else {
			tail.setNext(element);
			tail = element;
			
			
		}
	}

	public void delete (reading element) {


	}

	public reading getFirst(){

		return head;
	}

	public reading getLast() {
		return tail;

	}
	
	public void resetCurrent()
	{
		current = head;
	}
	
	public reading getNextElement() {
		reading c = current;
		if (current != null)
		{
			current=current.getNext();
		}
		else
		{
			return null;
		}
		return c;
		
	}
	
	public reading getNth(int index) {
		reading answer = null;
		current = head;
		for(int i=1;i<=index;i++) {
			answer = getNextElement();
		}
		return answer;
	}

	
}