package baekjoon.array;

import java.util.Scanner;

public class Test_3052 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[10];
        int cnt = 0;

        for(int i=0 ;i<10; i++) {
            arr[i] = 43;
        }

        for(int i=0; i<10; i++) {
            int num = sc.nextInt() % 42;

            for(int j=0; j<10; j++) {
                if(arr[j] == num) break;

                if(j == 9) arr[cnt++] = num;
            }
        }

        System.out.println(cnt);
    }
}
