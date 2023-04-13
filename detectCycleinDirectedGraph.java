class Solution {
    // Function to detect cycle in a directed graph.
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean vis[] = new boolean[V];
        boolean pathVis[] = new boolean[V];
        //has to implemented via dfs because of dfs goes to one path at a time
        for(int i=0;i<V;i++) {
            if(dfs(i,vis,pathVis,adj))
                return true;
        }
        return false;
    }
    boolean dfs(int node,boolean vis[],boolean pathVis[],ArrayList<ArrayList<Integer>> adj) {
        
        vis[node] = true;
        pathVis[node] = true;
        
        for(int n : adj.get(node)) {
            if(!vis[n] && !pathVis[n]) {
                vis[n] = true;
                pathVis[n] = true;
                if(dfs(n,vis,pathVis,adj))
                    return true;
            }
            else if(vis[n] && pathVis[n]) {
                return true;
            }
        }
        pathVis[node] = false;
        return false;
    }
}
