
public class WeightedQuickUnionUF {
	
    private int[] id;   // parent[i] = parent of i
    private int[] size;     // size[i] = number of sites in subtree rooted at i
    private int N;      // number of components
    private int trees;  // number of trees
    

    public WeightedQuickUnionUF(int n) {
        trees = N = n ;
        id = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            size[i] = 1;
        }
    }

    public int numTrees() {
        return trees;
    }
  

    public int find(int i) {
        validate(i);
        while (i != id[i])
            i = id[i];
        return i;
    }

    // validate that p is a valid index
    private void validate(int i) {
        if (i < 0 || i > N-1) {
            throw new IllegalArgumentException("index " + i + " is not between 0 and " + (N-1));  
        }
    }


    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }


    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        // make smaller root point to larger one
        if (size[rootP] < size[rootQ]) {
            id[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        else {
            id[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        trees--;
    }
    
    public String toString(){
    	String str = "";
    	
    	for (int i : id){
    		str += i + ",";
    	}
    	
    	return str;
    }
    
}

