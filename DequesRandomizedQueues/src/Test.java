
public class Test {

	public static void main(String[] args) {
		Deque<String> deque = new Deque<String>();

		
		deque.addFirst("Primero");
		deque.addLast("Cuarto");
		System.out.println(deque.removeLast());
		System.out.println(deque.removeLast());
	
		
		int i = 0;
		for(String string : deque){
			i++;
			System.out.println(i + ":" + string);
		}
		
		
	}

}
