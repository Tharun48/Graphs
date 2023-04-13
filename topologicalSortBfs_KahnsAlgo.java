class Solution
{
    //Function to return list containing vertices in Topological order. 
    static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) 
    {
        boolean vis[] = new boolean[V];
        int inDegree[] = new int[V];
        
        for(int i=0;i<V;i++) {
            for(int n : adj.get(i)) {
                inDegree[n]++;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i=0;i<V;i++) {
            if(inDegree[i]==0) {
                q.offer(i);
            }
        }
        int ans[] = new int[V];
        int i=0;
        while(!q.isEmpty()) {
            int curNode = q.peek();
            q.poll();
            ans[i++] = curNode;
            for(int n : adj.get(curNode)) {
                inDegree[n]--;
                if(inDegree[n]==0) 
                    q.offer(n);
            }
        }
        return ans;
    }
}
