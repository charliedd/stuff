
public class Test {

	public static void main(String[] args) {
		RandomizedQueue<String> rmd = new RandomizedQueue<String>();
		rmd.enqueue("6");
		rmd.enqueue("5");
		rmd.enqueue("4");
		rmd.enqueue("3");
		rmd.enqueue("2");
		rmd.enqueue("1");
		
		
		for(String str : rmd){
			System.out.println(str);
		}
		
		System.out.println("///////////////////////////////");
		
		for(String str : rmd){
			System.out.println(str);
		}
	}

}
