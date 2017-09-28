
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Percolation perc = new Percolation(3);
		
		
		perc.open(1, 1);
		System.out.println(perc.isFull(1,1));
		//perc.open(2, 1);
		//perc.open(3, 1);
		
		
		System.out.println(perc);
	}

}
