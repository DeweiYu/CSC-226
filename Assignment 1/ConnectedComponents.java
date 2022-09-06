import helper.*;

public class ConnectedComponents {

    private ArrayGraph AG; // the graph which we are interested if there exists a cycle or not

    // You may use any other private variables that you want
    public ConnectedComponents(ArrayGraph AG) {
        this.AG = AG;
    }

    // You may write other private helper functions if you want
    private void dfs(int cur, boolean[] visited) {
        for (int nxt : AG.neighbours(cur)) {
            if (!visited[nxt]) {
                visited[nxt] = true;
                dfs(nxt, visited);
            }
        }
    }

    // returns the number of connected components in the graph AG using DFS or BFS
    public int numConnectedComponents() {
        // student code goes here
        int size = AG.V();
        boolean[] visited = new boolean[size];
        int cnt = 0;
        for (int i = 0; i < size; i++) {
            if (!visited[i]) {
                cnt++;
                visited[i] = true;
                dfs(i, visited);
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        args = new String[] {"examplegraph.txt"};
        args = new String[] {"T1.txt"};
        In in = new In(args[0]);
        Graph G = new Graph(in);
        ArrayGraph AG = new ArrayGraph(G);
        ConnectedComponents cc = new ConnectedComponents(AG);

        // The numConnectedComponents() function will be the only function called to test your implementation
        System.out.println(cc.numConnectedComponents());
    }

}
