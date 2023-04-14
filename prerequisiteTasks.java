class Solution {
    public boolean isPossible(int N, int[][] prerequisites)
    {
        //Kahn's Algorithm
        List<List<Integer>> adj = new ArrayList<>();
        for(int i=0;i<N;i++) {
            adj.add(new ArrayList<>());
        }       
        for(int a[] : prerequisites ) {
            adj.get(a[0]).add(a[1]);
        }
        boolean vis[] = new boolean[N];
        
        //toposort
 
        int inDegree[] = new int[N];
        
        for(int i=0;i<N;i++) {
            for(int n : adj.get(i) ) {
                inDegree[n]++;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        
        for(int i=0;i<N;i++) {
            if(inDegree[i]==0)
                q.offer(i);
        }
        int count=0;
        while(!q.isEmpty()) {
            int curNode = q.poll();
            count++;
            for(int n : adj.get(curNode) ) {
                inDegree[n]--;
                if(inDegree[n]==0)
                    q.offer(n);
            }
        }
        return count==N;
    }
}
