import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item>  {
	int size;
	Node head;
	
	class Node{
		Node next;
		Item item;
	}
	
	public RandomizedQueue(){
		size = 0;
	   // construct an empty randomized queue
		}
	
	public boolean isEmpty(){
		return size == 0;
	   // is the randomized queue empty?
		}
	
	public int size(){
		return this.size;
	   // return the number of items on the randomized queue
		}
	
	public void enqueue(Item item){
		Node newNode = new Node();
		newNode.item = item;
		newNode.next = null;
		
		if (isEmpty()){
			this.head = newNode;
		}else{
			Node temp = head;
			head = newNode;
			head.next = temp;
		}
		
		
		size++;
	   // add the item
		}
	
	public Item dequeue(){
		if(isEmpty())return null;
		
		int pos = randomPos(this.size);
		
		Item rItem;
		
		if(pos == 0){
			rItem = removeFirst();
		}else if (pos == size - 1){
			rItem = removeLast();
		}else{
			Node currentNode = head;
			
			
			for(int i = 0; i < pos - 1; i++){
				currentNode = currentNode.next;
			}
			
			rItem = currentNode.next.item;
			currentNode.next = currentNode.next.next; 
		}
		
		return rItem;

		}
	
	private Item removeFirst(){
		if (isEmpty())return null;
		
		Item remItem = this.head.item;
		head = head.next;
		
		size--;
		return remItem;
		
	}
	
	private Item removeLast(){
		if (isEmpty())return null;
		
		
		Item rItem;
		
		if (size == 1){
			rItem = head.item;
			head = null;
		}else{
			Node currNode = head;
			while(currNode.next.next != null)currNode = currNode.next;
			rItem = currNode.next.item;
			currNode.next = null;
		}
		
		
		size--;
		return rItem;
	}
	
	
	private int randomPos(int limit){
		return StdRandom.uniform(0, limit);
	}
	
	//public Item sample(){
	   // return a random item (but do not remove it)
		//}
	//public Iterator<Item> iterator(){
	   // return an independent iterator over items in random order
		//return null;
	//}
}