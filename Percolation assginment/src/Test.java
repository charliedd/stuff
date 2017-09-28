
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Percolation perc = new Percolation(5);
		System.out.println(perc);
		perc.open(1, 6);
		
		System.out.println(perc.isOpen(1, 6));
		
		System.out.println(perc.percolates());
	}

}
