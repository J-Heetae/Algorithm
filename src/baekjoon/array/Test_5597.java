package baekjoon.array;

import java.util.Scanner;

public class Test_5597 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean[] check = new boolean[31];

        for(int i=0; i<28; i++) {
            check[sc.nextInt()] = true;
        }

        for(int i=1; i<31; i++) {
            if(!check[i]) System.out.println(i);
        }
    }
}
