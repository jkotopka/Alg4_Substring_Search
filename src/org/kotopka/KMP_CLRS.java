package org.kotopka;

import java.util.Arrays;

/**
 * {@code KMP_CLRS} - Version of the Knuth-Morris-Pratt string pattern matching algorithm
 * from <a href="https://mitpress.mit.edu/books/introduction-algorithms-third-edition">Introduction to Algorithms 3rd ed.</a>
 */
public class KMP_CLRS {

    public void matcher(String T, String P) {
        int n = T.length();
        int m = P.length();
        int[] pi = computePrefix(P);
        int q = 0;

        for (int i = 0; i < n; i++) {
            while (q > 0 && P.charAt(q) != T.charAt(i)) {
                q = pi[q - 1];
            }
            if (P.charAt(q) == T.charAt(i)) {
                q++;
            }
            if (q == m) {
                System.out.println("Pattern occurs with shift " + (i - m + 1));
                q = pi[q - 1];
            }
        }
    }

    private int[] computePrefix(String P) {
        int m = P.length();
        int[] pi = new int[m];
        int k = 0;

        for (int q = 1; q < m; q++) {
            while (k > 0 && P.charAt(k) != P.charAt(q)) {
                k = pi[k - 1];
            }
            if (P.charAt(k) == P.charAt(q)) {
                k++;
            }
            pi[q] = k;
        }

        return pi;
    }

    public static void main(String[] args) {
        KMP_CLRS k = new KMP_CLRS();
//        System.out.println(Arrays.toString(k.computePrefix("ababaca")));
        k.matcher("haystackneedleina", "needle");
        k.matcher("aabacabbcdeabacaqq", "abaca");
    }

}
