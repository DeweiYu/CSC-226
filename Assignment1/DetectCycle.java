import helper.*;

public class DetectCycle {

    private ArrayGraph AG; // the graph which we are interested if there exists a cycle or not

    // You may use any other private variables that you want
    public DetectCycle(ArrayGraph AG) {
        this.AG = AG;
    }

    private void dfs(int cur, int[] states, boolean[] results, int parent) {
        if (states[cur] == 0) {
            states[cur] = 1;
            for (int nxt : AG.neighbours(cur)) {
                // if there is a edge to its root, and the root is not its parent
                if (nxt != parent && states[nxt] == 1) {
                    results[0] = true;
                }
                dfs(nxt, states, results, cur);
            }
            states[cur] = 2;
        }
    }

    // You may write other private helper functions if you want
    // returns if there exists a cycle in the graph AG or not using either DFS or BFS
    public boolean existsCycle() {
        // student code goes here
        int size = AG.V();
        int[] states = new int[size];
        boolean[] results = new boolean[] {false};
        for (int i = 0; i < size; i++) {
            dfs(i, states, results, -1);
        }
        return results[0];
    }

    public static void main(String[] args) {
        args = new String[] {"examplegraph.txt"};
        args = new String[] {"T1.txt"};

        In in = new In(args[0]);
        Graph G = new Graph(in);
        ArrayGraph AG = new ArrayGraph(G);
        DetectCycle dc = new DetectCycle(AG);

        // The existsCycle() function will be the only function called to test your implementation
        System.out.println(dc.existsCycle());
    }

}
