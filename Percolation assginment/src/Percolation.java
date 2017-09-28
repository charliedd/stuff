import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private int n;
    private int top,bottom;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF fuf;
    private boolean[] tile; // false - closed site, true - open site;
    private int openSites;
	
	public Percolation(int n){
		//Return if n is less than 1
	   if (n < 1) throw new IllegalArgumentException("N must be at least 1");
	   
	   this.n = n;
	   openSites = 0;
	   
	   uf = new WeightedQuickUnionUF(n*n + 2);
	   fuf = new WeightedQuickUnionUF(n*n + 1);
	   tile = new boolean[n*n];  
	   
	   top = n*n ; //virtual top
	   bottom = n*n + 1; //virtual bottom
	   
	  
	}

	private boolean validateIndex(int i) {
	        if (i < 0 || i > n*n -1) 
	        return false;
	        
	        return true;
	    }
	
	public void open(int row, int col){
		
		if(row < 1 || col > n)
			throw new IllegalArgumentException("Ilegal arguments");
		
		int currentTile = getIndex(row,col);
		
		if (!validateIndex(currentTile)) //if index is out of bounds return
			return;
		
		if (!isOpen(row,col)){
			openSites++;
			tile[currentTile] = true;
		}
		else return;
		
		int topTile,botTile,leftTile,rightTile;
		
		topTile = getIndex(row-1,col);
		
		if(row == 1){
			fuf.union(currentTile,top);
			uf.union(currentTile, top);
		}else if(validateIndex(topTile) && tile[topTile]){
			fuf.union(currentTile, topTile);
			uf.union(currentTile, topTile);
		}
		
		leftTile = getIndex(row,col-1);
		
		if(col-1 >= 1 && validateIndex(leftTile) && tile[leftTile]){
			fuf.union(currentTile, leftTile);
			uf.union(currentTile, leftTile);
		}
		
		rightTile = getIndex(row,col+1);
		if(col+1 <= n && validateIndex(rightTile) && tile[rightTile]){
			fuf.union(currentTile, rightTile);
			uf.union(currentTile, rightTile);
		}
		
		botTile = getIndex(row+1,col);
		if(row == n){
			uf.union(currentTile, bottom);
		}else if(validateIndex(botTile) && tile[botTile]){
			fuf.union(currentTile, botTile);
			uf.union(currentTile, botTile);
		}
		

		
		// open site (row, col) if it is not open already
		//if ()
		
	}

	
	public boolean isOpen(int row, int col){
		// is site (row, col) open?
		return tile[getIndex(row,col)] ;
	}
	public boolean isFull(int row, int col){
		// is site (row, col) full?
		return fuf.connected(getIndex(row,col), top) ;
	}
	public int numberOfOpenSites(){
		// number of open sites
		return openSites;
	}
	public boolean percolates(){
		// does the system percolate?
		return uf.connected(top, bottom);
	}
	
	private int getIndex(int i, int j){
		i--;
		j--;
		return i*n + j; 
	}
	

}
