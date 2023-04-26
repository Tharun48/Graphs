class Solution {
      int findCity(int n, int m, int[][] edges,int distanceThreshold)
      {
        int cost[][] = new int[n][n];
        //O(M)  
        for(int i=0;i<m;i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int wt = edges[i][2];
            cost[u][v] = wt;
            cost[v][u] = wt;
        }
        //O(N * N)
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if(cost[i][j]==0) {
                    cost[i][j] = -1;
                }
            }
            cost[i][i] = 0;
        }
        //O(N * N * N)
        int matrix[][] = shortest_distance(cost);
        int ans = 0;
        int mini = n;
        for(int i=0;i<n;i++) {
            int cnt = 0;
            for(int j=0;j<n;j++) {
                if(matrix[i][j]<=distanceThreshold)
                    cnt++;
            }
            if(cnt==mini) {
                ans = i;
            }
            else if(mini>cnt) {
                ans = i;
                mini = cnt;
            }
        }
        return ans;
      }
    public int[][] shortest_distance(int[][] matrix)
    {
        int n = matrix.length;
        
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if(matrix[i][j]==-1) {
                    matrix[i][j] = (int)1e7;
                }
            }
        }
        for(int k=0;k<n;k++) {
            for(int i=0;i<n;i++) {
                for(int j=0;j<n;j++) {
                    matrix[i][j] = Math.min(matrix[i][j],matrix[i][k]+matrix[k][j]);
                }
            }
        }
        return matrix;
    }
}
