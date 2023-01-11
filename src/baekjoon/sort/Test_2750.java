package baekjoon.sort;

import java.util.Scanner;

public class Test_2750 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];

        for(int i=0; i<N; i++) {
            arr[i] = sc.nextInt();
        }

        for(int i=0; i<N; i++) {
            for(int j=1; j<N-i; j++) {
                if(arr[j-1] > arr[j]) {
                    int tmp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = tmp;
                }
            }
        }

        for(int a : arr) {
            System.out.println(a);
        }
    }
}
