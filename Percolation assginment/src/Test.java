
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Percolation perc = new Percolation(3);
		System.out.println(perc);
		perc.open(1, 2);
		perc.open(2, 2);
		perc.open(3, 3);
		perc.open(3,1);
		perc.open(3, 2);
		System.out.println(perc);
		System.out.println(perc.printuf());
		System.out.println(perc.percolates());
	}

}
