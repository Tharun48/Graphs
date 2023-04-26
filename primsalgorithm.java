class Pair {
    int node;
    int weight;
    Pair(int node,int weight) {
        this.node = node;
        this.weight = weight;
    }
}

class Heap {
    int distance;
    int node;
    int parent;
    Heap(int distance,int node,int parent) {
        this.distance = distance;
        this.node = node;
        this.parent = parent;
    }
}

class Solution{
	static int spanningTree(int V, int E, int edges[][]){
	    List<List<Pair>> adj = new ArrayList<>();
	    boolean vis[] = new boolean[V];
	    
	    for(int i=0;i<V;i++) {
	        adj.add(new ArrayList<>());
	    }
	    
	    for(int i=0;i<E;i++) {
	        adj.get(edges[i][0]).add(new Pair(edges[i][1],edges[i][2]));
	        adj.get(edges[i][1]).add(new Pair(edges[i][0],edges[i][2]));
	    }
	    
	    List<Pair> list = new ArrayList<>(); //for edges...
	    PriorityQueue<Heap> pq = new PriorityQueue<>((a,b)->a.distance-b.distance);
	    pq.offer(new Heap(0,0,-1));
	    
	    int sum = 0;
	    int cnt =0;
	    //E * log E + E log E 
	    while(!pq.isEmpty() ) {
	        int distance = pq.peek().distance;
	        int curNode = pq.peek().node;
	        int parent = pq.peek().parent;
	        pq.poll();
	        if(vis[curNode])
	            continue;
	        if(!vis[curNode]) {
	            vis[curNode] = true;
	            sum += distance;
	            cnt++;
	            if(parent!=-1) {
	                list.add(new Pair(curNode,parent));
	            }
	        }
          // E * log E
	        for(Pair p : adj.get(curNode) ) {
	            if(!vis[p.node])
	                pq.offer(new Heap(p.weight,p.node,curNode));
	        }
	    }
	    
	    //to display the edges...
	    for(int i=0;i<list.size();i++) {
	        System.out.println(list.get(i).node + "  " + list.get(i).weight);
	        System.out.println();
	    }
	    return sum;
	}
}
