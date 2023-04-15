class Solution
{
    public String findOrder(String [] dict, int N, int K)
    {
        List<List<Integer>> adj = new ArrayList<>();
        
        for(int i=0;i<K;i++) {
            adj.add(new ArrayList<>());
        }
        
        for(int i=0;i<N-1;i++) {
            String a = dict[i];
            String b = dict[i+1];
            
            int len = Math.min(a.length(),b.length());
            
            for(int j=0;j<len;j++) {
                if(a.charAt(j)!=b.charAt(j)) {
                    adj.get(a.charAt(j)-'a').add(b.charAt(j)-'a');
                    break;
                }
            }
        }
        
        Queue<Integer> q = new LinkedList<>();
        boolean vis[] = new boolean[K];
        
        //toposort
        int inDegree[] = new int[K];
        for(int i=0;i<K;i++) {
            for(int n : adj.get(i) ) {
                inDegree[n]++;
            }
        }
        
        for(int i=0;i<K;i++) {
            if(inDegree[i]==0)
                q.offer(i);
        }
        
        String s = "";
        int count=0;
        while(!q.isEmpty()) {
            int num = q.poll();
            int k = num + 'a' ;
            s += (char)k;
            count++;
            for(int n : adj.get(num) ) {
                inDegree[n]--;
                if(inDegree[n]==0)
                    q.offer(n);
            }
        }
        return s;
    }
}
