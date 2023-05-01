public class Disjoint {
    int parent[];
    int rank[];
    int size[];
    Disjoint(int n) {
        parent = new int[n+1];
        rank = new int[n+1];
        size = new int[n+1];
        for(int i=0;i<=n;i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }
    public int findUltimateParent(int node) {
        if(node==parent[node])
                return node;
        return parent[node] = findUltimateParent(parent[node]);
    }
    public void unionByRank(int u,int v) {
        int u_p_u = findUltimateParent(u);
        int u_p_v = findUltimateParent(v);
        if(u_p_u==u_p_v)
            return;
        if(rank[u_p_u]==rank[u_p_v]) {
            rank[u_p_u]++;
            parent[u_p_v] = u_p_u;
        }
        else if(rank[u_p_u]>rank[u_p_v]){
            parent[u_p_v] = u_p_u;
        }
        else {
            parent[u_p_u] = u_p_v;
        }
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
class Main {
    public static void main(String[] args) throws IOException {

        Disjoint dj = new Disjoint(7);
        dj.unionBySize(1,2);
        dj.unionBySize(2,3);
        dj.unionBySize(4,5);
        dj.unionBySize(6,7);
        dj.unionBySize(5,6);
        dj.unionBySize(3,7);

        if(dj.findUltimateParent(3)== dj.findUltimateParent(7)) {
            System.out.println("yes belong to same components");
        }
    }
}
//TC _> O(4 * alpha)
