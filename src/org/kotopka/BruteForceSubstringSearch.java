package org.kotopka;

public class BruteForceSubstringSearch {

    public static int search(String text, String pattern) {
        int textLen = text.length();
        int pattLen = pattern.length();

        for (int i = 0; i < textLen; i++) {
            int j;
            for (j = 0; j < pattLen; j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) break;
            }

            if (j == pattLen) return i;
        }

        return -1; // not found
    }

    public static void main(String[] args) {
        String text = "inahaystackneedleina";
        String pattern = "needle";

        System.out.println("Index of pattern 'needle': " + search(text, pattern));
        System.out.println("Index of pattern 'horse': " + search(text, "horse"));
    }
}
