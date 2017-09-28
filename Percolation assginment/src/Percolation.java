
public class Percolation {
	private int N;
    private int top;
    private int bottom;
    private WeightedQuickUnionUF uf;
    private byte[] tile; // 0 - closed site, 1 - open site, 2 - full site;
    private int sites;
	
	public Percolation(int n){
	   N= n;
	   sites = 1;
	   
	   uf = new WeightedQuickUnionUF(n*n + 2);
	   tile = new byte[n*n];  
	   
	   for(byte t : tile )t = 0;
	   
	   top = n*n ;
	   bottom = n*n + 1;
	   
	   connectTop();
	}
	
	private void connectTop(){
		System.out.println(N);
		for (int i = 0; i < N ; i++){
			uf.union(top, i);
		}
			
		
			
	
	}
	
	private boolean validate(int i) {
	        if (i < 0 || i > N*N-1) 
	        return false;
	        
	        return true;
	    }
	
	public void open(int row, int col){
		//if(percolates())return;
		
		int targetTile = encode(row,col);
		
		if (!validate(targetTile))
			return;
		
		if (tile[targetTile] == 0)tile[targetTile] = 1;
		else return;
		
		int topTile = encode(row-1,col);
		int botTile = encode(row+1,col);
		
		int leftTile;
		int rightTile;
		
		if (col == 1) leftTile = -1 ;
		else leftTile = encode(row,col-1);
		
		if(col == N ) rightTile = -1;
		else  rightTile = encode(row,col+1);
		
		System.out.println("target: "+targetTile+"Top: "+topTile +" Bot" + botTile+ " left" + leftTile + "right: " +rightTile);
		
		if(validate(topTile) && tile[topTile] != 0){
			uf.union(targetTile, topTile);
		}

		if(validate(botTile) && tile[botTile] != 0){
			uf.union(targetTile, botTile);
		}
		
		if(validate(leftTile) && tile[leftTile] != 0){
			uf.union(targetTile, leftTile);
		}
		
		if(validate(rightTile) && tile[rightTile] != 0){
			uf.union(targetTile, rightTile);
		}
		
		
		for(int i = 0; i < tile.length; i++){
			if(tile[i] == 1){
				if(uf.connected(top, i) ){
					tile[i] = 2;
				}
			}
		}
		
		// open site (row, col) if it is not open already
		//if ()
		
	}
	

	
	public boolean isOpen(int row, int col){
		// is site (row, col) open?
		return 1 == tile[encode(row,col)];
	}
	public boolean isFull(int row, int col){
		// is site (row, col) full?
		return 2 == tile[encode(row,col)] ;
	}
	public int numberOfOpenSites(){
		// number of open sites
		return sites;
	}
	public boolean percolates(){
		// does the system percolate?
		for (int i = N*N -1; i >= (N-1)*N;i--){
			if(uf.connected(top, i))
				return true;
		}
		
		return false;
	}
	
	private int encode(int i, int j){
		i--;
		j--;
		return i*N + j; 
	}
	
	public String toString(){
		String perc = "";
		
		for (int i = 0; i < N*N; i++){
			switch (tile[i]){
				case 0:
					perc += "X";
					break;
				case 1:
					perc += "O";
					break;
				case 2:
					perc += "F";
					break;
			}
			if (i != 0 && ((i+1) % N == 0))perc += "\n";
		}
		return perc;
	}
	
	public String printuf(){
		return uf.toString();
	}
}
