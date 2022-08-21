package Array;

import java.util.*;

//Array_격자판 최대합
public class test220821_33 {
    //runtime error 너무 오래 걸림
    /*public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int max = 0;
        int[][] arr = new int[n][n];

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        for(int i=0; i<n; i++) {
            int tmp = 0;
            for(int j=0; j<n; j++) {
                tmp += arr[i][j];
            }
            if(tmp > max) max = tmp;
        }

        for(int i=0; i<n; i++) {
            int tmp = 0;
            for(int j=0; j<n; j++) {
                tmp += arr[j][i];
            }
            if(tmp > max) max = tmp;
        }

        for(int i=0; i<n; i++) {
            int tmp = 0;
            tmp += arr[i][i];
            if(i==n-1) {
                if(tmp > max) max = tmp;
            }
        }

        for(int i=n-1; i>0; i++) {
            int tmp = 0;
            tmp += arr[i][i];
            if(i==n-1) {
                if(tmp > max) max = tmp;
            }
        }

        System.out.print(max);

    }*/
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int answer = 0;
        int sum1 =0, sum2 = 0;
        int[][] arr = new int[n][n];

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        for(int i=0; i<n; i++) {
            sum1 = sum2 = 0;
            for(int j=0; j<n; j++) {
                sum1 += arr[i][j];
                sum2 += arr[j][i];
            }
            answer = Math.max(answer, sum1);
            answer = Math.max(answer, sum2);
        }

        sum1 = sum2 = 0;

        for(int i=0; i<n; i++) {
            sum1 += arr[i][i];
            sum2 += arr[n-i-1][n-i-1];
        }

        answer = Math.max(answer, sum1);
        answer = Math.max(answer, sum2);

        System.out.println(answer);

    }
}
