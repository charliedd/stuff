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
		
		int a = StdRandom.uniform(0, size);
		
		for(int i = 0; i < a; i++){
			
		}
	
	   // remove and return a random item
		}
//	public Item sample(){
	   // return a random item (but do not remove it)
		//}
	//public Iterator<Item> iterator(){
	   // return an independent iterator over items in random order
		//return null;
	//	}
}