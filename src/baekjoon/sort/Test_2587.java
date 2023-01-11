package baekjoon.sort;

import java.util.Scanner;

public class Test_2587 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 5;
        int sum = 0;
        int[] arr = new int[n];

        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
            sum += arr[i];
        }

        System.out.println(sum / n);

        for(int i=1; i<n; i++) {
            int tmp = arr[i];
            int prev = i - 1;
            while((prev >= 0) && (arr[prev] > tmp)) {
                arr[prev + 1] = arr[prev];
                prev--;
            }
            arr[prev+1] = tmp;
        }

        System.out.println(arr[2]);
    }
}
