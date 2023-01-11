package baekjoon.sort;

import java.util.Scanner;

public class Test_25305 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int k = sc.nextInt();
        int[] score = new int[N];

        for(int i=0; i<N; i++) {
            score[i] = sc.nextInt();
        }

        for(int i=0; i<N-1; i++) {
            int max = Integer.MIN_VALUE;
            int idx = -1;

            for(int j=i; j<N; j++) {
                if(score[j] > max) {
                    max = score[j];
                    idx = j;
                }
            }

            score[idx] = score[i];
            score[i] = max;
        }

        System.out.println(score[k-1]);
    }
}
