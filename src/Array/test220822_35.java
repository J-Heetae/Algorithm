package Array;

import java.util.Scanner;

//Array_임시반장정하기
public class test220822_35 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int max = 0;
        int answer = 0;
        int[][] arr = new int[n][5];

        for(int i=0; i<n; i++ ) {
            for(int j=0; j<n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        for(int i=0; i<n; i++) {
            int cnt = 0;
            for(int j=0; j<n; j++) {
                for(int k=0; k<5; k++) {
                    if(arr[i][k] == arr[j][k]) {
                        cnt++;
                        break;
                    }
                }
            }
            if(cnt > max) {
                max = cnt;
                answer = i+1;
            }
        }
        System.out.print(answer);
    }
}