package baekjoon.recursion;

import java.util.Scanner;

public class BAEKJOON_G5_별찍기10_2447 {

    static char[][] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        arr = new char[N][N];

        star(0,0,N,false);

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                sb.append(arr[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void star(int x, int y, int N, boolean blank) {
        if(blank) {
            for(int i=x; i<x+N; i++) {
                for(int j=y; j<y+N; j++) {
                    arr[i][j] = ' ';
                }
            }
            return;
        }

        if(N == 1) {
            arr[x][y] = '*';
            return;
        }

        int size = N/3;
        int cnt = 0;
        for(int i=x; i<x+N; i += size) {
            for(int j=y; j<y+N; j += size) {
                cnt++;
                if(cnt == 5)
                    star(i,j,size,true);
                else
                    star(i,j,size,false);
            }
        }
    }
}
