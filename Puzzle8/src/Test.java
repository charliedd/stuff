import edu.princeton.cs.algs4.In;


public class Test {
	public static void main(String[] args) {

	    // create initial board from file
	    In in = new In(args[0]);
	    int n = in.readInt();
	    int[][] blocks = new int[n][n];
	    for (int i = 0; i < n; i++)
	        for (int j = 0; j < n; j++)
	            blocks[i][j] = in.readInt();
	    Board initial = new Board(blocks);
	    
	    System.out.println("initial: " + initial);
	    System.out.println("hamming: " + initial.hamming());
	    System.out.println(initial.isGoal());
	    System.out.println(initial.position0i + ":" + initial.position0j);
	    
//	    for(Board nb : initial.neighbors()){
//	    	System.out.println(nb);
//	    	System.out.println("Goal? " + nb.isGoal() );
//	    }
	
	    // solve the puzzle
	    Solver solver = new Solver(initial);
	    System.out.println(solver.solution());
//	    // print solution to standard output
//	    if (!solver.isSolvable())
//	        StdOut.println("No solution possible");
//	    else {
//	        StdOut.println("Minimum number of moves = " + solver.moves());
//	        for (Board board : solver.solution())
//	            StdOut.println(board);
	    }
	    
}