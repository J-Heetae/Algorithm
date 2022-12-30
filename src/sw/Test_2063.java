package sw;

import java.util.Arrays;
import java.util.Scanner;

public class Test_2063 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] score = new int[N];

        for(int i=0; i<N; i++) {
            score[i] = sc.nextInt();
        }

        Arrays.sort(score);

        System.out.println(score[N / 2]);
    }
}
