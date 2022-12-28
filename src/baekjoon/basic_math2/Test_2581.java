package baekjoon.basic_math2;

import java.util.Scanner;

public class Test_2581 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();

        int[] arr = new int[b+1];
        int sum = 0;
        int min = Integer.MAX_VALUE;


        for(int i=2; i<=b; i++) {
            if(arr[i] == 0) {
                if(i>=a) {
                    sum += i;
                    min = Math.min(min, i);
                }
                for(int j=i; j<=b; j+=i) arr[j]++;
            }
        }

        if(sum == 0) {
            System.out.print(-1);
        } else {
            System.out.println(sum);
            System.out.print(min);
        }
    }
}
