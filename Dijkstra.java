class Pair {
    int distance;
    int node;
    Pair(int distance,int node) {
        this.distance = distance;
        this.node = node;
    }
}

class Solution
{
    static int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S)
    {
        int dist[] = new int[V];
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b)->a.distance-b.distance);
        
        pq.offer(new Pair(0,S));
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[S] =0;
        
        while(!pq.isEmpty()) {
            Pair p = pq.peek();
            int node = p.node;
            int weight = p.distance;
            pq.poll();
            int size = adj.get(node).size();
           
            for(int i=0;i<size;i++) {
                int neighBournode = adj.get(node).get(i).get(0);
                int neighBourWeight = adj.get(node).get(i).get(1);
                if(dist[neighBournode] > weight + neighBourWeight ) {
                    dist[neighBournode] = weight + neighBourWeight;
                    pq.offer(new Pair(weight + neighBourWeight,neighBournode));
                }
            }
        }
        return dist;
    }
}
