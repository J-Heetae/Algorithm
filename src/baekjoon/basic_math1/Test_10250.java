package baekjoon.basic_math1;

import java.util.Scanner;

public class Test_10250 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        for(int i=0; i<num; i++) {
            int H = sc.nextInt();
            int W = sc.nextInt();
            int N = sc.nextInt();

            if(N % H == 0) {
                System.out.println((H * 100) + (N / H));
            } else {
                System.out.println(((N % H) * 100) + ((N / H) + 1));
            }
        }
    }
}