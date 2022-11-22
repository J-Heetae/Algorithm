package baekjoon.array;

import java.util.Scanner;

public class Test_3052 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        float sum = 0;
        int max = Integer.MIN_VALUE;
        int[] score = new int[num];

        for(int i=0 ;i<num; i++) {
            score[i] = sc.nextInt();
            if(score[i] > max) max = score[i];
        }

        for(int i=0; i<num; i++) {
            sum += (float) score[i] / max * 100;
        }

        float avg = (float) sum / num;

        System.out.println(avg);
    }
}
