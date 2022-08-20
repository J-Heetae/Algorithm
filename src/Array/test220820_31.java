package Array;

import java.util.*;

//Array_점수계산
public class test220820_31 {
    /*public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] test = new int[n];
        int[] score = new int[n];
        int answer = 0;

        for(int i=0; i<n; i++) {
            test[i] = sc.nextInt();
        }

        for(int i=0; i<n; i++) {
            if( test[i] == 0) {
                score[i] = 0;
            } else {
                if(i == 0) {
                    score[i] = 1;
                } else {
                    score[i] = score[i-1] + 1;
                }
            }
        }

        for(int s : score) {
            answer += s;
        }

        System.out.print(answer);
    }*/

    //배열 사용하지 않기
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        int cnt = 0;
        int answer = 0;
        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }

        for(int a : arr) {
            if(a == 1) {
                cnt++;
                answer += cnt;
            } else {
                cnt = 0;
            }
        }

        System.out.print(answer);

    }
}
