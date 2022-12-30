package sw;

import java.util.Scanner;

public class Test_1959 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int i=1; i<=T; i++) {
            int N = sc.nextInt();
            int M = sc.nextInt();

            int[] A = new int[N];
            int[] B = new int[M];

            for(int j=0; j<N; j++) {
                A[j] = sc.nextInt();
            }

            for(int j=0; j<M; j++) {
                B[j] = sc.nextInt();
            }

            if(N > M) System.out.println("#" + i + " " +getMax(B, A));
            else System.out.println("#" + i + " " +getMax(A, B));
        }
    }

    static int getMax(int[] A, int[] B) {
        int cnt = B.length - A.length;
        int max = Integer.MIN_VALUE;

        for(int i=0; i<=cnt; i++) {
            int sum = 0;

            for(int j=0; j<A.length; j++) {
                sum += A[j] * B[j + i];
            }

            max = Math.max(max, sum);
        }

        return max;
    }
}
