package org.kotopka;

public class BruteForceSubstringSearch {

    public static int search(String text, String pattern) {
        int N = text.length();
        int M = pattern.length();

        for (int i = 0; i <= N - M + 1; i++) {
            int j;
            for (j = 0; j < M; j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) break;
            }

            if (j == M) return i;
        }

        return -1; // not found
    }

    public static void main(String[] args) {
        String text = "inahaystackneedleina";
        String pattern = "needle";

        System.out.println("Index of pattern 'needle': " + search(text, pattern));
        System.out.println("Index of pattern 'horse': " + search(text, "horse"));
        System.out.println("Index of pattern 'eina': " + search(text, "eina"));
        System.out.println("Index of pattern 'inah': " + search(text, "inah"));
    }
}
