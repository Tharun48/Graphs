class Disjoint{
    int parent[];
    int size[];
    Disjoint(int n) {
        parent = new int[n];
        size = new int[n];
        
        for(int i=0;i<n;i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }
    int findUltimateParent(int n) {
        if(n==parent[n])
            return n;
        return parent[n] = findUltimateParent(parent[n]);
    }
    void unionBySize(int u,int v) {
        int u_p_u = findUltimateParent(u);
        int u_p_v = findUltimateParent(v);
        if(u_p_u==u_p_v)
            return;
        if(size[u_p_v]>size[u_p_u]) {
            parent[u_p_u] = u_p_v;
            size[u_p_v] += size[u_p_u];
        }
        else {
            parent[u_p_v] = u_p_u;
            size[u_p_u] += size[u_p_v];
        }
    }
}
class Solution{
	static int spanningTree(int V, int E, int edges[][]){
	    Disjoint dj = new Disjoint(V);
	    int minimumSum = 0;
	    Arrays.sort(edges,new Comparator<>() {
	        public int compare(int val1[],int val2[]) {
	                return val1[2]-val2[2];   
	        }
	    }
	    );
	    for(int i=0;i<E;i++) {
	        int node = edges[i][0];
	        int adjNode = edges[i][1];
	        int weight = edges[i][2];
	        if(dj.findUltimateParent(node)!=dj.findUltimateParent(adjNode)) {
	            dj.unionBySize(node,adjNode);
	            minimumSum += weight;
	        }
	    }
	    return minimumSum;
	}
}
