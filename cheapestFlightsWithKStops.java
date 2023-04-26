class Entry {
    int node;
    int weight;
    Entry(int node,int weight) {
        this.node = node;
        this.weight = weight;
    }
}
class Pair {
    int distance;
    int stops;
    int node;
    Pair(int stops,int distance,int node) {
        this.stops = stops;
        this.distance = distance;
        this.node = node;
    }
}
class Solution {
    public int CheapestFLight(int n,int flights[][],int src,int dst,int k) {
        List<List<Entry>> list = new ArrayList<>();
        for(int i=0;i<n;i++) {
            list.add(new ArrayList<>());
        }
        for(int i=0;i<flights.length;i++) {
            list.get(flights[i][0]).add(new Entry(flights[i][1],flights[i][2]));
        }
        Queue<Pair> pq = new LinkedList<>();
        //{stops,{distance,node}}
        pq.offer(new Pair(0,0,src));
        int dist[] = new int[n];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[src] = 0;
        //O(Total Edges)
        while(!pq.isEmpty()) {
            int curCity = pq.peek().node;
            int curDistance = pq.peek().distance;
            int stops = pq.peek().stops;
            pq.poll();
            if(stops>k)
                continue;
            for(Entry p : list.get(curCity) ) {
                int neighbourCity = p.node;
                int neightbourDistance = p.weight;
                if((dist[neighbourCity] > curDistance + neightbourDistance) && stops<=k) {
                    dist[neighbourCity] = curDistance + neightbourDistance;
                    pq.offer(new Pair(stops+1,curDistance + neightbourDistance,neighbourCity));
                }
            }
        }
        if(dist[dst]==Integer.MAX_VALUE)
            return -1;
        return dist[dst];
    }
}
