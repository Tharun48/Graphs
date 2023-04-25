class Pair {
    int effort;
    int row;
    int col;
    Pair(int effort,int row,int col) {
        this.effort = effort;
        this.row = row;
        this.col = col;
    }
}
class Solution {
    int MinimumEffort(int heights[][]) {
        int n = heights.length;
        int m = heights[0].length;
        
        int delRow[] = {1,-1,0,0};
        int delCol[] = {0,0,1,-1};
        
        
        PriorityQueue<Pair> q = new PriorityQueue<>((a,b)->a.effort-b.effort);
        int dist[][] = new int[n][m];
        
        for(int a[] : dist ) {
            Arrays.fill(a,Integer.MAX_VALUE);
        }
        //{path,row,col}
        q.offer(new Pair(0,0,0));
        
        dist[0][0] = 0;
        
        
        //O(E * log(V) )
        //O(N*M*4*log(N*M))
        while(!q.isEmpty()) {
            int curEffort = q.peek().effort;
            int row = q.peek().row;
            int col = q.peek().col;
            q.poll();
            
            if(row==n-1 && col==m-1)
                return curEffort;
            
            for(int i=0;i<4;i++) {
                int dRow = row + delRow[i];
                int dCol = col + delCol[i];
                
                if(dRow>=0 && dCol>=0 && dRow<n && dCol<m) {
                    int newEffort = Math.max(curEffort,Math.abs(heights[row][col]-heights[dRow][dCol]));
                    if(dist[dRow][dCol]>newEffort) {
                        dist[dRow][dCol] = newEffort;
                        q.offer(new Pair(newEffort,dRow,dCol));
                    }
                }
            }
        }
        //will never be executed
        return 0;
    }
}
