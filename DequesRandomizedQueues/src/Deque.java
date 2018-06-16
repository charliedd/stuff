import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
	private int size;
	private Node head,last;

	
	private class Node{
		Node next;
		Item item;
	}
	
	public Deque(){
		size = 0;
		// construct an empty deque
	}
	
	public boolean isEmpty(){
		return size == 0;
		// is the deque empty?
	}
	
	public int size(){
		return size;
		// return the number of items on the deque
	}
	
	public void addFirst(Item item){
		
		if(item == null){
			throw new java.lang.IllegalArgumentException();
		}
		
		Node newNode = new Node();
		newNode.item = item;
		newNode.next = null;
		
		if (isEmpty()){
			this.head = newNode;
			this.last = newNode;
		}else{
			Node temp = head;
			head = newNode;
			head.next = temp;
		}
		
		
		size++;
	}
	
	public void addLast(Item item){
		
		if(item == null)throw new java.lang.IllegalArgumentException();
		
		Node newNode = new Node();
		newNode.item = item;
		newNode.next = null;

		
		if (isEmpty()){
			this.head = newNode;
		}else{
			//Node currNode = this.head;
			//while(currNode.next != null)currNode = currNode.next;
			Node oldLast = this.last;
			last = newNode;
			oldLast.next = last;
		}
		
		size++;
	}
	
	public Item removeFirst() {
		if (isEmpty())throw new java.util.NoSuchElementException();
		
		Item remItem = this.head.item;
		head = head.next;
		
		size--;
		return remItem;
		
	}
	
	public Item removeLast() {
		if (isEmpty()) throw new java.util.NoSuchElementException();
		
		
		Item rItem;
		
		if (size == 1) {
			rItem = head.item;
			head = null;
		} else {
			Node currNode = head;
			while(currNode.next.next != null)currNode = currNode.next;
			rItem = currNode.next.item;
			currNode.next = null;
		}
		
		
		
		size--;
		return rItem;
	}
	
	public Iterator<Item> iterator() {
		//Node first = this.first;
		
		return new Iterator<Item>() {

            Node currentNode = head;

            @Override
            public boolean hasNext() {
            	return currentNode != null;
            }

            @Override
            public Item next() {
            	if (!hasNext())throw new java.util.NoSuchElementException();
            
            	Item item = currentNode.item;
            	currentNode = currentNode.next;
           
                return item;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
	}
	

}
