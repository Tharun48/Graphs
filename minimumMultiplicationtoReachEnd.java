class Pair {
    int node;
    int steps;
    Pair(int node,int steps) {
        this.node = node;
        this.steps = steps;
    }
}
class Solution {
    int minimumMultiplications(int[] arr, int start, int end) {
        int dist[] = new int[100000];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[start]=0;
        Queue<Pair> q = new LinkedList();
        q.offer(new Pair(start,0));
        int mod = 100000;
        int cnt =0 ;
        //O(100000 * N )
        while(!q.isEmpty()) {
            int node = q.peek().node;
            int steps = q.peek().steps;
            q.poll();
            if(node==end) {
                return steps;
            }
            for(int n : arr ) {
                cnt++;
                int neighBourNode = (node * n) % mod;
                if(dist[neighBourNode]>steps+1) {
                    dist[neighBourNode] = steps + 1;
                    q.offer(new Pair(neighBourNode,steps+1));
                }
            }
        }
        return -1;
    }
}
