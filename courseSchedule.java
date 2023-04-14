class Solution
{
    static boolean dfs(int node,List<List<Integer>> adj,boolean vis[],boolean pathVis[],Stack<Integer> stack) {
        vis[node] = true;
        pathVis[node] = true;
        for(int n : adj.get(node) ) {
            if(!vis[n]) {
                vis[n] = true;
                pathVis[n] = true;
                if(dfs(n,adj,vis,pathVis,stack))
                    return true;
            }
            else if(pathVis[n])
                return true;
        }
        pathVis[node] = false;
        stack.push(node);
        return false;
    }

    static int[] findOrder(int n, int m, ArrayList<ArrayList<Integer>> prerequisites) 
    {
        boolean vis[] = new boolean[n];
        boolean pathVis[] = new boolean[n];
        List<List<Integer>> adj = new ArrayList<>();
        
        for(int i=0;i<n;i++) {
            adj.add(new ArrayList<>());
        }
        
        for(int i=0;i<prerequisites.size();i++) {
            adj.get(prerequisites.get(i).get(0)).add(prerequisites.get(i).get(1));
        }
        
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<n;i++) {
            if(!vis[i]) {
                if(dfs(i,adj,vis,pathVis,stack))
                    return new int[]{};
            }
        }
        int ans[] = new int[n];
        int i=0;
        
        for(int k : stack ) {
            ans[i++] = k;
        }
        return ans;
    }
}
