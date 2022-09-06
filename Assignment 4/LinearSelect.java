import java.util.*;

public class LinearSelect {

    // run in O(n)
    static int partition(int[] a, int pivot) {

        // find the index of pivot
        int i = 0;
        while (i < a.length) {
            if (a[i] == pivot) {
                break;
            }
            i++;
        }
        // put the pivot at start
        a[i] = a[0];
        a[0] = pivot;
        int l = 0;
        int r = a.length - 1;
        while (l < r) {
            while (l < r && pivot <= a[r]) {
                r--;
            }
            a[l] = a[r];
            while (l < r && a[l] <= pivot) {
                l++;
            }
            a[r] = a[l];
        }
        a[l] = pivot;
        return l;
    }

    // returns the kth smallest element in S
    static int LinearSelect(int[] S, int k) {
        assert 1 <= k && k <= S.length;
        return LinearSelect0(S, k - 1);
    }

    // helper method of LinearSelect, the index begin at 0.
    static int LinearSelect0(int[] S, int k) {

        if (S.length == 1) {
            return S[0];
        }

        int pivot = MedianOfMedians(S);
        int idx = partition(S, pivot);

        // split S into L and G, by povit
        int[] L = new int[idx];
        for (int i = 0; i < idx; i++) {
            L[i] = S[i];
        }
        int[] G = new int[S.length - idx - 1];
        for (int i = 0; i < S.length - idx - 1; i++) {
            G[i] = S[i + idx + 1];
        }

        if (k < idx) {
            return LinearSelect0(L, k);
        } else if (k == idx) {
            return S[idx];
        } else {
            return LinearSelect0(G, k - 1 - idx);
        }

    }

    // returns the median of medians pivot (with groups of size 5) of S
    static int MedianOfMedians(int[] S) {
        int pivot = 0;

        int groups = (S.length - 1) / 5 + 1;
        int[] R = new int[groups];
        for (int i = 0; i < groups; i++) {
            int start = i * 5;
            int end = start + 5;
            if (end > S.length) {
                end = S.length;
            }

            // sort the length-5 array
            for (int j = start; j < end; j++) {
                for (int k = start; k < end - (j - start) - 1; k++) {
                    if (S[k] > S[k + 1]) {
                        int t = S[k];
                        S[k] = S[k + 1];
                        S[k + 1] = t;
                    }
                }
            }
            // get the median
            R[i] = S[(start + end - 1) / 2];
        }
        
        // sort the medians
        pivot = LinearSelect(R, (groups - 1) / 2 + 1);
        return pivot;
    }

    public static void main(String[] args) {
        // Example input
        int[] SS = {8, 3, 13, 16, 20, 9, 1, 3, 11, 17, 10, 18, 12, 4, 15, 8, 15, 6, 21, 7, 5, 14, 20, 2, 19, 22, 3};
        // Sorted: 1, 2, 3, 3, 3, 4, 5, 6, 7, 8, 8, 9, 10, 11, 12, 13, 14, 15, 15, 16, 17, 18, 19, 20, 20, 21, 22

        System.out.println(LinearSelect(SS, 19)); // the 19th smallest number in S is 15

        System.out.println(LinearSelect(SS, 1)); // the 1st smallest number in S is 1

        System.out.println(LinearSelect(SS, 27)); // the 27th smallest number in S is 22
        int size = 500;
        Random rand = new Random();

        int[] S = new int[size];
        int[] R = new int[size];
        for (int i = 0; i < size; i++) {
            R[i] = S[i] = rand.nextInt(size);
        }
        Arrays.sort(R);
        Arrays.sort(S);
        for (int i = 0; i < size; i++) {
            int n = LinearSelect(Arrays.copyOf(S, size), i + 1);
            assert n == R[i];
        }
    }

}
