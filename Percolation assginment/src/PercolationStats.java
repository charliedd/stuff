

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	private double[] results;
	private double confidenceHi, confidenceLo, mean,dev,confidence;
	
	
	   public PercolationStats(int n, int trials) {
		   // perform trials independent experiments on an n-by-n grid
		   results = new double[trials];
		   int row,col;
		   
		   
		   for (int i = 0; i < trials ; i++){
			   Percolation perc = new Percolation(n);
			   do{
				   row = StdRandom.uniform(1,n+1);
				   col = StdRandom.uniform(1,n+1);
				   perc.open(row, col);
			   }while(!perc.percolates());
			   results[i] = (double)(n*n)/perc.numberOfOpenSites();
		   }
		   mean = StdStats.mean(results);
		   dev = StdStats.stddev(results);
		   confidence = (1.96 * stddev()) / Math.sqrt(trials);
		   confidenceHi = mean + confidence;
		   confidenceLo = mean - confidence;
		   
	   }
	   public double mean(){
		   return mean;
		   // sample mean of percolation threshold
	   }
	   public double stddev(){
	   		return dev;
		   // sample standard deviation of percolation threshold
	   }
	   public double confidenceLo(){
		    return confidenceLo;
		   // low  endpoint of 95% confidence interval
	   }
	   public double confidenceHi(){
		   return confidenceHi;
		   // high endpoint of 95% confidence interval
	   }

	   public static void main(String[] args){
		   // test client (described below)
		   int N = Integer.parseInt(args[0]);
	       int T = Integer.parseInt(args[1]);
		   
	       PercolationStats stats = new PercolationStats(N, T);
	       System.out.println("mean                    = " + stats.mean());
	       System.out.println("stddev                  = " + stats.stddev());
	       System.out.println("95% confidence interval = " + stats.confidenceLo() + ", " + stats.confidenceHi());
	   }
	
}