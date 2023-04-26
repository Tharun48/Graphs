class Pair {
    int row;
    int col;
    int distance;
    Pair(int row,int col,int distance) {
        this.row = row;
        this.col = col;
        this.distance = distance;
    }
}
class Solution {
    boolean isValid(int row,int col,int n,int m,int grid[][]) {
        if(row>=0 && col>=0 && row<n && col<m && grid[row][col]==1)
            return true;
        return false;
    }
    int shortestPath(int[][] grid, int[] source, int[] destination) {
        int delRow[] = {1,-1,0,0};
        int delCol[] = {0,0,1,-1};
        
        int n = grid.length;
        int m = grid[0].length;
        
        int dist[][] = new int[n][m];
        
        for(int a[] : dist ) {
            Arrays.fill(a,Integer.MAX_VALUE);
        }
        
        dist[source[0]][source[1]] = 0;
        //Queue is sufficient as the distance(equal in all cases) from cell to another cell will be 1.
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(source[0],source[1],0));
        //O(N*4)
        while(!q.isEmpty()) {
            int row = q.peek().row;
            int col = q.peek().col;
            int distance = q.peek().distance;
            q.poll();
            if(row==destination[0] && col==destination[1])
                return distance;
            for(int i=0;i<4;i++) {
                int dRow = row + delRow[i];
                int dCol = col + delCol[i];
                if(isValid(dRow,dCol,n,m,grid) && dist[dRow][dCol]> distance + 1) {
                    dist[dRow][dCol] = distance + 1;
                    q.offer(new Pair(dRow,dCol,distance+1));
                }
            }
        }
        return -1;
    }
}
