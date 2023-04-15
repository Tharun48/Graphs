class Pair {
    int node;
    int weight;
    Pair(int node,int weight) {
        this.node = node;
        this.weight = weight;
    }
}

class Solution {
    
    void topoSort(int node,List<List<Pair>> adj,Stack<Integer> stack,boolean vis[]) {
        
        List<Pair> list = adj.get(node);
        for(Pair p : list ) {
            if(!vis[p.node]) {
                vis[p.node] = true;
                topoSort(p.node,adj,stack,vis);
            }
        }
        stack.push(node);
    }
    
	public int[] shortestPath(int N,int M, int[][] edges) {
	
		List<List<Pair>> adj = new ArrayList<>();
		
		for(int i=0;i<N;i++) {
		    adj.add(new ArrayList<>());
		}

		for(int i=0;i<M;i++) {
		    adj.get(edges[i][0]).add(new Pair(edges[i][1],edges[i][2]));
		}
		
		boolean vis[] = new boolean[N];
		
		Stack<Integer> stack = new Stack<>();
		
		//toposort
		//O(N+M)
		topoSort(0,adj,stack,vis);
	
		int ans[] = new int[N];
		
		Arrays.fill(ans,Integer.MAX_VALUE);
		ans[0] = 0;
		
		//updating weights
		//O(N+M)
		while(!stack.isEmpty()) {
		    int curNode = stack.peek();
		    stack.pop();
		    for(int i=0;i<adj.get(curNode).size();i++) {
		        int n = adj.get(curNode).get(i).node;
		        int wt = adj.get(curNode).get(i).weight;
		        ans[n] = Math.min(ans[n],ans[curNode] + wt);
		    }
		}
		
		for(int i=0;i<ans.length;i++) {
		    if(ans[i]==Integer.MAX_VALUE)
		        ans[i] = -1;
		}
		return ans;
	}
}
