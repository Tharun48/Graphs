class Pair {
    int node;
    int weight;
    Pair(int node,int weight) {
        this.node = node;
        this.weight = weight;
    }
}

class Solution {
	public int[] shortestPath(int N,int M, int[][] edges) {
		List<List<Pair>> adj = new ArrayList<>();
		
		for(int i=0;i<N;i++) {
		    adj.add(new ArrayList<>());
		}

		for(int i=0;i<M;i++) {
		    adj.get(edges[i][0]).add(new Pair(edges[i][1],edges[i][2]));
		}
		
// 		for(int i=0;i<adj.size();i++) {
// 		    for(int j=0;j<adj.get(i).size();j++) {
// 		        Pair p = adj.get(i).get(j);
// 		        System.out.print("node = " + p.node);
// 		        System.out.print(" weight = " + p.weight);
// 		        System.out.println();
// 		    }
// 		}
		
		int ans[] = new int[N];
		Arrays.fill(ans,Integer.MAX_VALUE);
		helper(0,0,adj,ans); 
		for(int i=0;i<ans.length;i++) {
		    if(ans[i]==Integer.MAX_VALUE)
		        ans[i] = -1;
		}
		return ans;
	}
	void helper(int src,int weight,List<List<Pair>> adj,int ans[]) {
	    ans[src] = Math.min(ans[src],weight);
	    List<Pair> list = adj.get(src);
	    for(Pair p : list )  {
	        helper(p.node,weight+p.weight,adj,ans);
	    }
	}
}
