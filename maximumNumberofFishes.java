class Pair {
    int score;
    int row;
    int col;
    Pair(int score,int row,int col) {
        this.score = score;
        this.row = row;
        this.col = col;
    }
}
class Solution {
    public int findMaxFish(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean vis[][] = new boolean[n][m];
        int ans = 0;
        //O(N * M * N * M * 4)
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(grid[i][j]>0) {
                    ans = Math.max(ans,bfs(i,j,grid,vis));
                    for(boolean a[] : vis )
                        Arrays.fill(a,false);
                }
            }
        }
        return ans;
    }
    int bfs(int row,int col,int grid[][],boolean vis[][]) {
        int score = 0;
        int n = grid.length;
        int m = grid[0].length;
        int delRow[] = {1,-1,0,0};
        int delCol[] = {0,0,1,-1};
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(grid[row][col],row,col));
        while(!q.isEmpty()) {
            row = q.peek().row;
            col = q.peek().col;
            score += q.peek().score;
            q.poll();
            vis[row][col] = true;
            for(int i=0;i<4;i++) {
                int dRow = row + delRow[i];
                int dCol = col + delCol[i];
                if(dRow>=0 && dCol>=0 && dRow<n && dCol<m && grid[dRow][dCol]>0 && !vis[dRow][dCol]) {
                    vis[dRow][dCol] = true;
                    q.offer(new Pair(grid[dRow][dCol],dRow,dCol));
                }
            }
        }
        return score;
    }
}
