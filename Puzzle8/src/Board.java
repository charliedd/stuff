import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Board {
	 private int[][] tiles;
	 private int n;
	 private int lastTile;
	 private boolean isGoal;
	 private int manhattanScore;
	 private int hammingScore;
	 int position0i;
	 int position0j;
	 
	 public Board(int[][] blocks){
		tiles = blocks;
		this.n = tiles.length;
		this.lastTile = n * n;
		this.isGoal = checkIfGoal();
		 // construct a board from an n-by-n array of blocks
		this.hammingScore = getHamming();
		this.manhattanScore = getManhattan();
		//System.out.println("i: " + this.position0i + " j: " + this.position0j);
	 }
	 
	 private boolean checkIfGoal(){
		 boolean isGoal = true;
		 int pos = 1;
		 
		 for(int i = 0; i < n; i++)
			 for(int j = 0; j < n; j++){
				 
				 if(this.tiles[i][j] == 0){					
					 this.position0i = i;
					 this.position0j = j;
				 }
				 
				 if (pos == lastTile)pos = 0;
				 
				 if(pos != this.tiles[i][j])isGoal = false;
					
				 pos++;
			 }
		 return isGoal;
	 }
	 
     // (where blocks[i][j] = block in row i, column j)
	 public int dimension(){
		 return n;
		 // board dimension n
	 }
	 
	 public int hamming(){
		 return this.hammingScore;
	 }
 
	 public int manhattan(){

		 return this.manhattanScore;
	 }
	 
	 private int getHamming(){
		 int blocksWrong = 0;
		 int pos = 1;
		 
		 for(int i = 0; i < n; i++)
			 for(int j = 0; j < n; j++){
				 int tile = this.tiles[i][j];
				 
				 if(tile == 0){
					 pos++;
					 continue;
				 }
				 if(tile != pos++){
					 blocksWrong++;
				 }
				
			 }
		 return blocksWrong;
		 // number of blocks out of place
	 }
	 
	 private int getManhattan(){
		 int manhDistance = 0;
		 int expectedTile = 1;
		 
		 for(int i = 0; i < n; i++)
			 for(int j = 0; j < n; j++){
				int tile = this.tiles[i][j];
				
				if(tile == 0){
					 expectedTile++;
					 continue;
				 }
				 
				if(tile != expectedTile++){
					 int expectedI = tile/n;
					 int expectedJ = tile%n;
					 
					 if(expectedJ == 0){
						 expectedI--;
						 expectedJ = n - 1;
					 }else{
						 expectedJ--;
					 }
					 

					 
					 manhDistance += (Math.abs(expectedI - i) + Math.abs(expectedJ - j));
				 }
				
			 }
		// System.out.println("--------------");
		 return manhDistance;
		 // sum of Manhattan distances between blocks and goal
	 }
	 
	 public boolean isGoal(){
		 return this.isGoal;
		 // is this board the goal board?
	 }
	 
	 public Board twin(){
		 Board twinBoard = new Board(this.tiles);
		 int i = 0;
		 int j = 0;
		 while(true){
			 if(j == n){
				 j = 0;
				 i++;
			 }
			 
			 if(i == n)return null;
			 
			 if(twinBoard.tiles[i][j] != 0 && twinBoard.tiles[i][j + 1] != 0){
				 swapTiles(twinBoard.tiles, i,j,i,j+1);
				 return twinBoard;
			 }
		 }
		 
		 // a board that is obtained by exchanging any pair of blocks
	 }
	 
	 private void swapTiles(int[][] blocks , int x1, int y1, int x2, int y2){
		 int temp = blocks[x1][y1];
		 blocks[x1][y1] = blocks[x2][y2];
		 blocks[x2][y2] = temp;
	 }
	 
	 private void swapTiles(int x1, int y1, int x2, int y2){
		 int temp = this.tiles[x1][y1];
		 this.tiles[x1][y1] = this.tiles[x2][y2];
		 this.tiles[x2][y2] = temp;
	 }
	 
	 private int[][] clone2dArray(int[][] ogArray){
		 int[][] values = ogArray.clone();
		 for (int i = 0; i < values.length; i++) {
		   values[i] = values[i].clone();
		 }
		 return values;
	 }
	 
	 public boolean equals(Object y){
		   if (y==this) return true;
	        if (y==null || !(y instanceof Board) || ((Board)y).tiles.length != tiles.length) return false;
	        for (int row = 0; row < tiles.length; row++)
	            for (int col = 0; col < tiles.length; col++)
	                if (((Board) y).tiles[row][col] != tiles[row][col]) return false;

	        return true;
		 // does this board equal y?
	 }
	 
	 public Iterable<Board> neighbors(){
		  return this.getNeighbours();
	 }
	 
	 private List<Board> getNeighbours(){
		 List<Board> neighBours = new ArrayList<Board>();
		 
		 //Check left neighbour
		 if(this.position0j - 1 >= 0 ){
			 //System.out.println("left nb");
			 int[][] tempTiles = clone2dArray(tiles);
			 swapTiles(tempTiles, this.position0i , this.position0j, this.position0i, this.position0j - 1);
			 Board leftNeighbour = new Board(tempTiles);
			 //System.out.println(leftNeighbour);
			 
			 neighBours.add(leftNeighbour);
		 }
		
		if(this.position0j + 1 < n ){
			 //System.out.println("right nb");
			 int[][] tempTiles = clone2dArray(tiles);
			 swapTiles(tempTiles, this.position0i , this.position0j, this.position0i, this.position0j + 1);
			 Board rightNeighbour = new Board(tempTiles);
			 neighBours.add(rightNeighbour);
		 }
		 
		if(this.position0i - 1 >= 0 ){
			 //System.out.println("top nb");
			 int[][] tempTiles = clone2dArray(tiles);
			 swapTiles(tempTiles,this.position0i , this.position0j, this.position0i - 1, this.position0j);
			 Board topNeighbour = new Board(tempTiles);
			// System.out.println(topNeighbour);
			 neighBours.add(topNeighbour);
		 }
		 
		 if(this.position0i + 1 < n ){
			 //System.out.println("down nb");
			 int[][] tempTiles = clone2dArray(tiles);
			 swapTiles(tempTiles, this.position0i , this.position0j, this.position0i + 1, this.position0j);
			 Board botNeighbour = new Board(tempTiles);
			 neighBours.add(botNeighbour);
		 }
			
		 return neighBours;
	 }
	 
	 public String toString(){
		   StringBuilder s = new StringBuilder();
		    s.append(n + "\n");
		    for (int i = 0; i < n; i++) {
		        for (int j = 0; j < n; j++) {
		            s.append(String.format("%2d ", tiles[i][j]));
		        }
		        s.append("\n");
		    }
		    return s.toString();
		 // string representation of this board (in the output format specified below)
	 }

}