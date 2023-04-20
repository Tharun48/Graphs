class Pair {
    int node;
    int weight;
    Pair(int node,int weight) {
        this.node = node;
        this.weight = weight;
    }
}
class Solution {
    public static List<Integer> shortestPath(int n, int m, int edges[][]) {
        
        List<List<Pair>> list = new ArrayList<>();

        for(int i=0;i<=n;i++) {
            list.add(new ArrayList<>());
        }

        for(int i=0;i<edges.length;i++) {
            list.get(edges[i][0]).add(new Pair(edges[i][1],edges[i][2]));
            list.get(edges[i][1]).add(new Pair(edges[i][0],edges[i][2]));
        }

        PriorityQueue<Pair> pq= new PriorityQueue<>((a,b)->a.weight-b.weight);
        pq.offer(new Pair(1,0));

        int dist[] = new int[n+1];
        int parent[] = new int[n+1];

        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[1] = 0;
        
        for(int i=1;i<=n;i++) {
            parent[i] = 1;
        }

        while(!pq.isEmpty()) {
            Pair p = pq.peek();
            pq.poll();
            int curNode = p.node;
            int curWeight = p.weight;
            List<Pair> neighbourList = list.get(curNode);
            for(Pair pair : neighbourList ) {
                if(dist[pair.node] > curWeight + pair.weight) {
                    dist[pair.node] = curWeight + pair.weight;
                    parent[pair.node] = curNode;
                    pq.offer(new Pair(pair.node,curWeight + pair.weight));
                }
            }
        }
        
        List<Integer> noPath = new ArrayList<>();
        noPath.add(-1);
        
        if(dist[n]==Integer.MAX_VALUE)
            return noPath;

        List<Integer> path = new ArrayList<>();
        int node = n;
        
        while(node!=parent[node]) {
            path.add(node);
            node = parent[node];
        }
        path.add(1);
        Collections.reverse(path);
        return path;
    }
}
