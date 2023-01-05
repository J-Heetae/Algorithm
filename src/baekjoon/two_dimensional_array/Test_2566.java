package baekjoon.two_dimensional_array;

import java.util.Scanner;

public class Test_2566 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int max = Integer.MIN_VALUE;
        int r = 0;
        int c = -1;
        int[] idx = new int[2];

        for(int i=0; i<81; i++) {
            c++;

            if(c == 9) {
                c = 0;
                r++;
            }

            int n = sc.nextInt();

            if(n > max) {
                max = n;
                idx[0] = r +1;
                idx[1] = c + 1;
            }
        }

        System.out.printf("%d\n%d %d", max, idx[0], idx[1]);
    }
}
