package baekjoon.recursion;

import java.math.BigInteger;
import java.util.Scanner;

public class BAEKJOON_S1_하노이탑_1914 {

    static StringBuilder sb = new StringBuilder();
    static double cnt;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        if(N <= 20)
            hanoi(N, 1, 2, 3);

        System.out.println(getLeastCnt(N));
        if(N <= 20)
            System.out.println(sb);
    }

    private static BigInteger getLeastCnt(int N) {
        return new BigInteger("2").pow(N).subtract(new BigInteger("1"));
    }

    private static void hanoi(int N, int s, int m, int e) {
        if(N == 0) return;

        hanoi(N - 1, s, e, m);
        cnt++;
        sb.append(s + " " + e +"\n");
        hanoi(N - 1, m, s, e);
    }
}
