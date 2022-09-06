import helper.*;

public class ArrayGraph {

    private int[] storage; // stores the graph as a flat array
    private int n;         // number of vertices
    private int m;         // number of edges

    // You may use as many other private variables as you want. 
    // However, one dimentional arrays are the only collection type that you may use.
    private int[] startIndex;

    public ArrayGraph(Graph G) {
        n = G.V(); // get the number of vertices in G
        m = G.E(); // get the number of edges in G

        // student code to convert Graph into a flat array
        startIndex = new int[n];
        storage = new int[m * 2];
        int i = 0;
        // the following code illustrates how to iterate through the neighbours v adjacent to a vertex u in G
        for (int u = 0; u < n; u++) {
            startIndex[u] = i;
            for (int v : G.adj(u)) {
                storage[i++] = v;
            }
        }
        // assert i == storage.length;
    }

    // returns the number of vertices in G
    public int V() {
        // student code
        return n;
    }

    // returns the number of edges in G
    public int E() {
        // student code
        return m;
    }

    // returns the degree of a vertex v
    public int deg(int v) {
        // student code
        int end;
        if (v + 1 >= V()) {
            end = storage.length;
        } else {
            end = startIndex[v + 1];
        }
        return end - startIndex[v];
    }

    // returns an array of size deg(v) containing the neighbours of v
    public int[] neighbours(int v) {
        // student code
        int end;
        if (v + 1 >= V()) {
            end = storage.length;
        } else {
            end = startIndex[v + 1];
        }
        int size = end - startIndex[v];
        int[] ret = new int[size];
        for (int i = 0; i < size; i++) {
            ret[i] = this.storage[i + startIndex[v]];
        }
        return ret;
    }

    // returns if vertices u and v are adjacent or not
    public boolean isAdj(int u, int v) {
        for (int i : this.neighbours(v)) {
            if (i == u) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        /*
        args = new String[] {"examplegraph.txt"};
        args = new String[] {"T1.txt"};
        In in = new In(args[0]);
        Graph G = new Graph(in);
        ArrayGraph AG = new ArrayGraph(G);
        // code to test your implementation of ArrayGraph here
        java.util.Random rand = new java.util.Random(0);
        for (int i = 0; i < G.V(); i++) {
            assert G.degree(i) == AG.deg(i);
        }
        for (int i = 0; i < 100; i++) {
            // int u = rand.nextInt(G.V());
            int v = rand.nextInt(G.V());
            int[] nei = AG.neighbours(v);
            java.util.TreeSet<Integer> set1 = new java.util.TreeSet<Integer>();
            for (int t : nei) {
                set1.add(t);
            }
            java.util.TreeSet<Integer> set2 = new java.util.TreeSet<Integer>();
            for (int t : G.adj(v)) {
                set2.add(t);
            }
            assert (set1.equals(set2));
        }
        for (int i = 0; i < 100; i++) {
            int u = rand.nextInt(G.V());
            int v = rand.nextInt(G.V());

            java.util.TreeSet<Integer> set2 = new java.util.TreeSet<Integer>();
            for (int t : G.adj(u)) {
                set2.add(t);
            }
            assert AG.isAdj(u, v) == set2.contains(v);
        }
            */
    }

}
