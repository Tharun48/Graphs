class Solution
{
    public void shortest_distance(int[][] matrix)
    {
        int n = matrix.length;
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if(matrix[i][j]==-1) {
                    matrix[i][j] = (int)1e7;
                }
            }
        }
        //O(N * N * N)
        for(int k=0;k<n;k++) {
            for(int i=0;i<n;i++) {
                for(int j=0;j<n;j++) {
                    matrix[i][j] = Math.min(matrix[i][j],matrix[i][k]+matrix[k][j]);
                }
            }
        }
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if(matrix[i][j]==(int)1e7) {
                    matrix[i][j] =-1;
                }
            }
        }
    }
}

//Multi source Shortest path.
// if dist[i][i] != 0 then there exist negative cycle in the graphs.
