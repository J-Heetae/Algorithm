package baekjoon.basic_math1;

import java.util.Scanner;

public class Test_2775 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for(int i=0; i<n; i++) {
            int f = sc.nextInt();
            int r = sc.nextInt();

            int[][] apart = new int[f+1][r+2];

            for(int j=1; j<r+1; j++) {
                apart[0][j] = j;
            }

            for(int j=1; j<f+1; j++) {
                for(int k=1; k<r+1; k++) {
                    int cnt = 1;
                    while(cnt <= k) {
                        apart[j][k] += apart[j-1][cnt];
                        cnt++;
                    }
                }
            }

            System.out.println(apart[f][r]);
        }
    }
}
