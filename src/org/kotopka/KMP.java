package org.kotopka;

/**
 * {@code KMP} - Knuth-Morris-Pratt substring search method using a Discrete Finite-state Automaton from <a href="https://algs4.cs.princeton.edu/home/">Algorithms 4th ed.</a>
 */
public class KMP {

    private final String pat;
    private final int[][] dfa;

    public KMP(String pat) {
        this.pat = pat;
        int m = pat.length();
        int R = 256; // Extended ASCII alphabet
        dfa = new int[R][m];
        dfa[pat.charAt(0)][0] = 1; // init the first match char to advance dfa to state 1
        for (int x = 0, j = 1; j < m; j++) {
            // compute dfa
            for (int c = 0; c < R; c++) {
                dfa[c][j] = dfa[c][x];
            }
            dfa[pat.charAt(j)][j] = j + 1; // character match state transition
            x = dfa[pat.charAt(j)][x];
        }
    }

    public int search(String txt) {
        // simulate operation of dfa on txt
        int i, j, n = txt.length(), m = pat.length();
        for (i = 0, j = 0; i < n && j < m; i++) {
            j = dfa[txt.charAt(i)][j];
        }
        if (j == m) {
            return i - m;
        }
        else {
            return n;
        }
    }

    public void printDFA() {
        System.out.println("DFA:");
        System.out.printf("%3c", ' ');
        for (char c : pat.toCharArray()) {
            System.out.printf("%3c", c);
        }
        System.out.println();

        for (int i = 'A'; i <= 'Z'; i++) {
            System.out.printf("%3c", (char)i);
            for (int col : dfa[i]) {
                System.out.printf("%3d", col);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        String pat = args[0];
        String txt = args[1];
        KMP kmp = new KMP(pat);
        System.out.println("text:    " + txt);
        int offset = kmp.search(txt);
        System.out.print("pattern: ");
        for (int i = 0; i < offset; i++) {
            System.out.print(" ");
        }
        System.out.println(pat);
        System.out.println();
        kmp.printDFA();
    }
}
