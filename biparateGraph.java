class Pair {
    int node;
    int color;
    Pair(int node,int color) {
        this.node = node;
        this.color = color;
    }
}

class Solution
{
    public boolean isBipartite(int V, ArrayList<ArrayList<Integer>>adj)
    {
        boolean vis[] = new boolean[V];
        
        //color a[]
        
        int color[] = new int[V];
        
        for(int i=0;i<V;i++) {
            if(!vis[i] && !bfs(i,vis,color,adj)) {
                return false;
            }
        }
        return true;
    }
    //breadth first search
    boolean bfs(int row,boolean vis[],int color[],ArrayList<ArrayList<Integer>>adj) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(row,1));
        while(!q.isEmpty()) {
            int node = q.peek().node;
            int col = q.peek().color;
            q.poll();
            color[node] = col;
            vis[node] = true;
            for(int n : adj.get(node)) {
                if(!vis[n]) {
                    if(col==1) {
                        vis[n] = true;
                        color[n] =2; 
                        q.add(new Pair(n,2));
                    }
                    else {
                        vis[n] = true;
                        color[n] = 1;
                        q.add(new Pair(n,1));
                    }
                }
                else if(color[n]==col) {
                    return false;
                }
            }
            
        }
        return true;
    }
    //depth first search
    boolean dfs(int row,int col,boolean vis[],int color[],ArrayList<ArrayList<Integer>>adj) {
        
        color[row] = col;
        for(int n : adj.get(row)) {
            if(!vis[n]) {
                if(col==1) {
                    vis[n] = true;
                    if(!dfs(n,2,vis,color,adj))
                        return false;
                }
                else {
                    vis[n] = true;
                    if(!dfs(n,1,vis,color,adj))
                        return false;
                }
            }
            else if(col==color[n]) {
                return false;
            }
        }
        return true;
    }
}
class Main {
  public static void main(String args[]) {
    Solution s = new Solution();
    System.out.println(s.isBipartite(5,adj));
  }

}
