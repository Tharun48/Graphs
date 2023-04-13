class Solution {

    int numberOfEnclaves(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;
        
        
        boolean vis[][] = new boolean[n][m];
        
        int delRow[] = {-1,1,0,0};
        int delCol[] = {0,0,-1,1};
        
        
        
        Queue<Pair> q = new LinkedList<>();
        
        //cells which are connected to boundary ones have grid[0...n][0...m]=1
        //they can move out
        
        //first row
        for(int i=0;i<m;i++) {
            if(grid[0][i]==1 && !vis[0][i]) {
                vis[0][i] = true;
                q.add(new Pair(0,i));
            }
        }
        
        //last row 
        
        for(int i=0;i<m;i++) {
            if(grid[n-1][i]==1 && !vis[n-1][i]) {
                q.add(new Pair(n-1,i));
                vis[n-1][i] = true;
            }
                
        }
        
        //first column
        for(int i=0;i<n;i++) {
            if(grid[i][0]==1 && !vis[i][0]) {
                q.add(new Pair(i,0));
                vis[i][0] = true;
            }
                
        }
        
        //last column
        for(int i=0;i<n;i++) {
            if(grid[i][m-1]==1 && !vis[i][m-1]) {
                vis[i][m-1] = true;
                q.add(new Pair(i,m-1));
            }
                
        }
        
        while(!q.isEmpty()) {
            int row = q.peek().row;
            int col = q.peek().col;
            q.poll();
            vis[row][col] = true;
            
            for(int i=0;i<4;i++) {
                int r = row + delRow[i];
                int c = col + delCol[i];
                
                if(r>=0 && c>=0 && r<n && c<m && grid[r][c]==1 && !vis[r][c]) {
                    vis[r][c] = true;
                    q.add(new Pair(r,c));
                }
            }
            
        }
        int ans =0;
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(!vis[i][j] && grid[i][j]==1)
                    ans++;
            }
        }
        return ans;
    }
}

Class Main {
  public static void main(String args[]) {
      Scanner sc = new Scanner(System.in);
      Solution s = new Solution();
      int grid[][] = {{0,0,0,0},{1,0,1,0},{0,1,1,0},{0,0,0,0}};
      System.out.println(s.numberOfEnclaves(grid));
  }
}

