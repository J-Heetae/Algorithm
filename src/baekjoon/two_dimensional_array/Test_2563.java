package baekjoon.two_dimensional_array;

import java.util.Scanner;

public class Test_2563 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] paper = new int[101][101];
        int cnt = 0;

        for(int i=0; i<n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();

            for(int j=0; j<10; j++) {
                for(int k=0; k<10; k++) {
                    if(paper[x+1+j][y+1+k] == 0) cnt++;
                    paper[x+1+j][y+1+k]++;
                }
            }
        }

        System.out.println(cnt);
    }
}
