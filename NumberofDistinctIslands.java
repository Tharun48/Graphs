class Pair {
    int row;
    int col;
    Pair(int row,int col) {
        this.row = row;
        this.col = col;
    }
}
class Solution {

    int countDistinctIslands(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        
        boolean vis[][] = new boolean[n][m];
        
        int delRow[] = {-1,1,0,0};
        int delCol[] = {0,0,1,-1};
        
        Set<List<String>> set = new HashSet<>();
        
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(grid[i][j]==1 && !vis[i][j]) {
                    List<String> list = new ArrayList<>();
                    bfs(i,j,list,grid,vis,delRow,delCol);
                    set.add(list);
                }
            }
        }
        return set.size();
    }
    
    void bfs(int row,int col,List<String> list,int grid[][],boolean vis[][],int delRow[],int delCol[]) {

        int n = grid.length;
        int m = grid[0].length;
        
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(row,col));
        list.add(toString(row-row,col-col));
        
        while(!q.isEmpty()) {
            int rowD = q.peek().row;
            int colD = q.peek().col;
            q.poll();
            vis[rowD][colD] = true;
            for(int i=0;i<4;i++) {
                int r = rowD + delRow[i];
                int c = colD + delCol[i];
                if(r>=0 && c>=0 && r<n && c<m && grid[r][c]==1 && !vis[r][c]) {
                    list.add(toString(r-row,c-col));
                    vis[r][c] = true;
                    q.add(new Pair(r,c));    
                }
            }
        }
    }
    
    String toString(int n,int m) {
        return Integer.toString(n) + " " + Integer.toString(m);
    }
}
class Main {
  public static void main(String args[]) {
    Solution s = new Solution();
    int grid[][] = {{1, 1, 0, 0, 0},
            {1, 1, 0, 0, 0},
            {0, 0, 0, 1, 1},
            {0, 0, 0, 1, 1}};
    System.out.println(s.countDistinctIslands(grid));
  }
}
