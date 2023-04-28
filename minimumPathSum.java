class Pair{
    int distance;
    int row;
    int col;
    Pair(int distance,int row,int col) {
        this.distance = distance;
        this.row = row;
        this.col = col;
    }
}
class Solution {
    public int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int dist[][] = new int[n][m];
        for(int a[] : dist )
            Arrays.fill(a,Integer.MAX_VALUE);
        dist[0][0] = grid[0][0];

        int delRow[] = {1,0};
        int delCol[] = {0,1};
        //dijkstra's
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b)->a.distance-b.distance);
        pq.offer(new Pair(grid[0][0],0,0));

        while(!pq.isEmpty()) {
            int dis = pq.peek().distance;
            int row = pq.peek().row;
            int col = pq.peek().col;
            pq.poll();
            if(row==n-1 && col==m-1)
                return dis;
            for(int i=0;i<2;i++) {
                int dRow = delRow[i] + row;
                int dCol = delCol[i] + col;
                if(dRow>=0 && dCol>=0 && dRow<n && dCol<m && dist[dRow][dCol]> dis + grid[dRow][dCol]) {
                    dist[dRow][dCol] = dis + grid[dRow][dCol];
                    pq.offer(new Pair(dis + grid[dRow][dCol],dRow,dCol));
                }
            }
        }
        return dist[n-1][m-1];
    }
}
