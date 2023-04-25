class Pair {
    int node;
    int weight;
    Pair(int node,int weight) {
        this.node = node;
        this.weight = weight;
    }
}
class Solution {
    static int[] bellman_ford(int V, ArrayList<ArrayList<Integer>> edges, int S) {
        int dist[] = new int[V];
        Arrays.fill(dist,(int)1e8);
        dist[S] = 0;
        //N-1 relaxations...
        //O(N * E )
        for(int i=1;i<V;i++) {
            for(ArrayList<Integer> al : edges ) {
                int u = al.get(0);
                int v = al.get(1);
                int weight = al.get(2);
                if(dist[u]!=(int)1e8 && dist[v]>dist[u]+ weight) {
                    dist[v] = dist[u] + weight;
                }
            }
        }
        //for Cycle detection
        //if the Nth iteration tends to change the distance array
        //then graph contains negative edge cycle...
        //O(E)
        for(ArrayList<Integer> al : edges ) {
                int u = al.get(0);
                int v = al.get(1);
                int weight = al.get(2);
            if(dist[u]!=(int)1e8 && dist[v]>dist[u]+ weight) {
                return new int[]{-1};
            }
        }
        return dist;    
    }
}
