package baekjoon.array;

import java.util.Scanner;

public class Test_4344 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        for(int i=0; i<num; i++) {
            int stu = sc.nextInt();
            int sum= 0;
            int[] score = new int[stu];

            for(int j=0; j<stu; j++) {
                score[j] = sc.nextInt();
                sum += score[j];
            }

            float avg = (float) sum / stu;
            int good = 0;

            for(int j=0; j<stu; j++) {
                if(score[j] > avg) good++;
            }

            float tmp = (float) (Math.round( ((float)good / stu * 100) * 1000) / 1000.0);

            System.out.println(String.format("%.3f", tmp) + "%");
        }
    }
}