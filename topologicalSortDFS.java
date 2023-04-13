class Solution
{
    //Function to return list containing vertices in Topological order. 
    static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) 
    {
        Stack<Integer> stack = new Stack<>();
        boolean vis[] = new boolean[V];
        for(int i=0;i<V;i++) {
            if(!vis[i])
                dfs(i,stack,vis,adj);
        }
        int ans[] = new int[stack.size()];
        int i=0;
        while(stack.size()>0) {
            ans[i++] = stack.pop();
        }
        return ans;
    }
    static void dfs(int node,Stack<Integer> stack,boolean vis[],ArrayList<ArrayList<Integer>> adj) {
        vis[node] =true;
        for(int n : adj.get(node) ) {
            if(!vis[n]) {
                vis[n] = true;
                dfs(n,stack,vis,adj);
            }
        }
        stack.push(node);
    }
}
