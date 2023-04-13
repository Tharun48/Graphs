class Solution {
    
    List<Integer> eventualSafeNodes(int V, List<List<Integer>> adj) {
        boolean vis[] = new boolean[V];
        boolean pathVis[] = new boolean[V];
        boolean ans[] = new boolean[V];
        for(int i=0;i<V;i++) {
            if(!vis[i]) {
                dfs(i,vis,pathVis,adj,ans);
            }
        }
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<V;i++) {
            if(ans[i])
                list.add(i);
        }
        return list;
    }
    boolean dfs(int node,boolean vis[],boolean pathVis[],List<List<Integer>> adj,boolean ans[]) {
        vis[node] = true;
        pathVis[node] = true;
        for(int n : adj.get(node)) {
            if(!vis[n] && !pathVis[n]) {
                vis[n] = true;
                pathVis[n] = true;
                if(dfs(n,vis,pathVis,adj,ans))
                    return true;
            }
            else if(vis[n] && pathVis[n]) {
                return true;
            }
        }
        ans[node] = true;
        pathVis[node] = false;
        return false;
    }
}
