package org.kotopka;

import java.math.BigInteger;
import java.util.Random;

/**
 * {@code RabinKarp} - Implementation of the Rabin-Karp substring search algorithm,
 * from <a href=https://algs4.cs.princeton.edu/home/>Algorithms 4th ed.</a> by Robert Sedgewick and Kevin Wayne
 */
public class RabinKarp {
    private String pat; // required for Las Vegas version
    private long patHash;
    private int m;
    private long q;
    private int R;
    private long RM;

    public RabinKarp(String pat) {
        this.pat = pat;
        this.m = pat.length();
        this.q = longRandomPrime();
        this.R = 256;
        this.RM = 1;

        for (int i = 1; i <= m - 1; i++) {
            RM = (R * RM) % q;
        }

        patHash = hash(pat, m);
    }

    private long longRandomPrime() {
        BigInteger bi = BigInteger.probablePrime(31, new Random());
        return bi.longValue();
    }

    // Las Vegas version
    public boolean check(String txt, int i) {
        for (int j = 0; j < m; j++) {
            if (pat.charAt(j) != txt.charAt(i + j)) {
                return false;
            }
        }

        return true;
    }

    // Monte Carlo version
//    public boolean check(String txt, int i) {
//        return true;
//    }

    private long hash(String key, int m) {
        long h = 0;

        for (int j = 0; j < m; j++) {
            h = (R * h + key.charAt(j)) % q;
        }

        return h;
    }

    public int search(String txt) {
        int n = txt.length();
        long txtHash = hash(txt, m);

        if ((patHash == txtHash) && check(txt,0)) {
            return 0; // match
        }

        for (int i = m; i < n; i++) {
            // remove leading digit, add trailing digit, check for match
            txtHash = (txtHash + q - RM * txt.charAt(i - m) % q) % q;
            txtHash = (txtHash * R + txt.charAt(i)) % q;

            int offset = i - m + 1;
            if ((patHash == txtHash) && (check(txt, offset))) {
                return offset; // match;
            }
        }

        return n; // no match
    }

    public static void main(String[] args) {
        String pat = args[0];
        String txt = args[1];

        RabinKarp rk = new RabinKarp(pat);

        int offset = rk.search(txt);
        System.out.println("Offset: " + offset);

        System.out.println("text:    " + txt);
        System.out.print("pattern: ");
        for (int i = 0; i < offset; i++) {
            System.out.print(" ");
        }
        System.out.println(pat);
    }

}
