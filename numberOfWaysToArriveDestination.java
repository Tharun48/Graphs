class Pair {
    int node;
    long distance;
    Pair(int node,long distance) {
        this.node = node;
        this.distance = distance;
    }
}
class Solution {
    static int countPaths(int n, List<List<Integer>> roads) {
        List<List<Pair>> adj = new ArrayList<>();
        long dist[] = new long[n]; // as time constaints are 1 <=time<= 1e9 
        int ways[] = new int[n];
        Arrays.fill(dist,Long.MAX_VALUE);
        
        //O(N)
        for(int i=0;i<n;i++) {
            adj.add(new ArrayList<>());
        }
        
        //O(M)
        for(int i=0;i<roads.size();i++) {
            adj.get(roads.get(i).get(0)).add(new Pair(roads.get(i).get(1),roads.get(i).get(2)));
            adj.get(roads.get(i).get(1)).add(new Pair(roads.get(i).get(0),roads.get(i).get(2)));
        }
        
        //{node,distance}
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b)->(int)a.distance-(int)b.distance);
        pq.offer(new Pair(0,0));
        dist[0] = 0;
        ways[0] = 1;
        
        
        //O(M * log(N) )
        while(!pq.isEmpty()) {
            int node =  pq.peek().node;
            long distance = pq.peek().distance;
            pq.poll();
            List<Pair> cur = adj.get(node);
            for(Pair p : cur ) {
                int neighBourNode = p.node;
                long neighBourdistance = p.distance;
                if(dist[neighBourNode] > neighBourdistance + distance) {
                    dist[neighBourNode] = neighBourdistance + distance;
                    ways[neighBourNode] = ways[node];
                    pq.offer(new Pair(neighBourNode,neighBourdistance + distance));
                }
                else if(dist[neighBourNode] == neighBourdistance + distance) {
                    ways[neighBourNode] =(ways[neighBourNode] + ways[node]);
                }
            }
        }
        return ways[n-1];
    }
}
