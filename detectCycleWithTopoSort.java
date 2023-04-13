class Solution {
    // Function to detect cycle in a directed graph.
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean vis[] = new boolean[V];
        Queue<Integer> q = new LinkedList<>();   
        int inDegree[] = new int[V];
        for(int i=0;i<V;i++) {
            for(int n : adj.get(i)) {
                inDegree[n]++;
            }
        }        
        for(int i=0;i<V;i++) {
            if(inDegree[i]==0)
                q.offer(i);
        }       
        while(!q.isEmpty()) {
            int curNode = q.peek();
            q.poll();
            vis[curNode] = true;
            for(int n : adj.get(curNode)) {
                inDegree[n]--;
                if(inDegree[n]==0)
                    q.offer(n);
            }
        }     
        for(int i=0;i<V;i++) 
            if(!vis[i])
                return true;
        return false;
    }  
}
