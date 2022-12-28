package baekjoon.basic_math2;

import java.util.Scanner;

public class Test_1929 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(true) {
            int n = sc.nextInt();

            //입력의 마지막은 0
            if(n == 0) break;

            int[] arr = new int[2 * n + 1];
            int cnt = 0;

            for (int i = 2; i <= 2 * n; i++) {
                if (arr[i] == 0) {
                    for (int j = i; j <= 2 * n; j += i) arr[j]++;
                    if (i > n) cnt++;
                }
            }
            System.out.println(cnt);
        }
    }
}