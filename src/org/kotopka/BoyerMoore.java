package org.kotopka;

public class BoyerMoore {

    private int[] right;
    private String pat;

    public BoyerMoore(String pat) {
        // compute skip table
        this.pat = pat;
        int m = pat.length();
        int R = 256; // extended ASCII
        this.right = new int[R];

        for (int c = 0; c < R; c++) {
            right[c] = -1;  // -1 for chars not in pattern
        }

        for (int j = 0; j < m; j++) {
            right[pat.charAt(j)] = j;
        }
    }

    public int search(String txt) {
        // search for pattern in text
        int n = txt.length();
        int m = pat .length();
        int skip;

        for (int i = 0; i <= n - m; i += skip) {
            // does the pattern match the text at position i?
            skip = 0;
            for (int j = m - 1; j >= 0; j--) {
                if (pat.charAt(j) != txt.charAt(i + j)) {
                    skip = j - right[txt.charAt(i + j)];
                    if (skip < 1) skip = 1;
                    break;
                }

            }

            if (skip == 0) return i;
        }

        return n;
    }

    public static void main(String[] args) {
        final String pat = "NEEDLE";
        final String txt = "FINDINAHAYSTACKNEEDLE";

        BoyerMoore b = new BoyerMoore(pat);
        System.out.println(b.search(txt));
    }

}
