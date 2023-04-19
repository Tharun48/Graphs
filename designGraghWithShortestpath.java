class Pair {
    int node;
    int weight;
    Pair(int node,int weight) {
        this.node = node;
        this.weight = weight;
    }
}

class Graph {
    List<List<Pair>> list = new ArrayList<>();
    public Graph(int n, int[][] edges) {
        for(int i=0;i<n;i++) {
            list.add(new ArrayList<>());
        }

        for(int i=0;i<edges.length;i++) {
            list.get(edges[i][0]).add(new Pair(edges[i][1],edges[i][2]));
        }

    }
    
    public void addEdge(int[] edge) {
        list.get(edge[0]).add(new Pair(edge[1],edge[2]));
    }
    
    public int shortestPath(int node1, int node2) {
        int vis[] = new int[list.size()];
        Arrays.fill(vis,-1);
        int ans = dijkstra(node1,node2,list.size());
        if(ans==Integer.MAX_VALUE)
            return -1;
        return ans;
    }

    public int dijkstra(int S,int node2,int V)
    {
        int dist[] = new int[V];
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b)->a.weight-b.weight);
        
        pq.offer(new Pair(S,0));
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[S] =0;
        
        while(!pq.isEmpty()) {
            Pair p = pq.peek();
            int node = p.node;
            int weight = p.weight;
            pq.poll();
            List<Pair> al = list.get(node);
            
            for(int i=0;i<al.size();i++) {
                int neighBournode = list.get(node).get(i).node;
                int neighBourWeight = list.get(node).get(i).weight;
                if(dist[neighBournode] > weight + neighBourWeight ) {
                    dist[neighBournode] = weight + neighBourWeight;
                    pq.offer(new Pair(neighBournode,weight + neighBourWeight));
                }
            }
        }
        return dist[node2];
    }
    
}
