public class Disjoint {
    int parent[];
    int size[];
    Disjoint(int n) {
        parent = new int[n+1];
        size = new int[n+1];
        for(int i=1;i<=n;i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }
    public int findUltimateParent(int node) {
        if(node==parent[node])
                return node;
        return parent[node] = findUltimateParent(parent[node]);
    }
    public void unionBySize(int u,int v) {
        int u_p_u = findUltimateParent(u);
        int u_p_v = findUltimateParent(v);
        if (u_p_u == u_p_v)
            return;
        if (size[u_p_u] > size[u_p_v]) {
            size[u_p_u] += size[u_p_v];
            parent[u_p_v] = u_p_u;
        } else {
            size[u_p_v] += size[u_p_u];
            parent[u_p_u] = u_p_v;
        }
    }
}
class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        Disjoint dj = new Disjoint(n);
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if(i!=j && isConnected[i][j]==1) {
                    dj.unionBySize(i+1,j+1);
                }
            }
        }
        Set<Integer> set = new HashSet<>();
        for(int i=1;i<=n;i++) {
            int a = dj.findUltimateParent(i);
            set.add(a);
        }
        return set.size();
    }
}
//can be done using component traversal
