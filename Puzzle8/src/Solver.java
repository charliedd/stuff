import java.util.Arrays;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver {
	Node solution;
	
	private class Node implements Comparable<Node>{
		Board board;
		Node predecessor;
		int moves;
		
		
		Node(Board board){
			this.board = board;
			this.predecessor = null;
			this.moves = 0;
		}
		
		Node(Board board, Node predecessor, int moves){
			this.board = board;
			this.predecessor = predecessor;
			this.moves = moves;
		}

		@Override
		public int compareTo(Node otherNode) {
			// TODO Auto-generated method stub
			
			
			if(this.board.manhattan() + this.moves < otherNode.board.manhattan() + otherNode.moves){
				return -1;
			}else if(this.board.manhattan() + this.moves > otherNode.board.manhattan() + otherNode.moves){
				return 1;
			}
			return 0;
		}
	}
	
	public Solver(Board initial){
		MinPQ<Node> queue = new MinPQ<Node>();
		Node currentNode = new Node(initial);
		queue.insert(currentNode);
		
		for(int i = 0; i < 5 ; i++){
			currentNode = queue.delMin();
			System.out.println(currentNode.board);
			if(currentNode.board.isGoal()){
				System.out.println("Encontre la solucion");
				solution = currentNode;
				break;
			}
			for(Board neighbor : currentNode.board.neighbors()){
				if(currentNode.predecessor!= null){
					if(neighbor.equals(currentNode.predecessor.board))continue;
				}
				
				Node newNode = new Node(neighbor,currentNode,currentNode.moves+1);
				queue.insert(newNode);
			}
		
			
		}
		
		solution = currentNode;
		// find a solution to the initial board (using the A* algorithm)
	}
//    public boolean isSolvable(){
//    	// is the initial board solvable?
//    }
// public int moves(){
//    	// min number of moves to solve initial board; -1 if unsolvable
//    }
	
	  public String solution(){
		  String strSolution = "";
		  Stack<Board> solutionStack = new Stack<Board>();
		  Node currentNode = solution;
		  while(currentNode != null){
			  solutionStack.push(currentNode.board);
			  currentNode = currentNode.predecessor;
		  }
		  
		  while(!solutionStack.isEmpty()){
			  strSolution += solutionStack.pop();
		  }
		  return strSolution + "\n en "+ solution.moves + " movimientos";
		  
	  }
//    public Iterable<Board> solution(){
//    	// sequence of boards in a shortest solution; null if unsolvable
//    }
//    public static void main(String[] args){
//    	// solve a slider puzzle (given below)
//    }
}