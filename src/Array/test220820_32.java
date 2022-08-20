package Array;

import java.util.*;

public class test220820_32 {
    /*public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] score = new int[n];

        for (int i = 0; i < n; i++) {
            score[i] = sc.nextInt();
        }

        int[] sorted = score.clone();

        for(int i=n-1; i>=0; i--) {
            for(int j=i-1; j>=0; j--) {
                int tmp = 0;
                if(sorted[i] > sorted[j]) {
                    tmp = sorted[i];
                    sorted[i] = sorted[j];
                    sorted[j] = tmp;
                }
            }
        }
        System.out.println(Arrays.toString(sorted));
        System.out.println(Arrays.toString(score));

        for(int s : score) {
            for(int i = 0; i<n; i++) {
                if( s == sorted[i]) {
                    System.out.print(i + 1 + " ");
                    break;
                }
            }
        }
    }*/
    
    //cnt 써서 더 간단하게
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] score = new int[n];

        for (int i = 0; i < n; i++) {
            score[i] = sc.nextInt();
        }

        for(int i=0; i<n; i++) {
            int cnt = 1;
            for(int s : score) {
                if(score[i] < s) cnt++;
            }
            System.out.print(cnt + " ");
        }
    }
}
