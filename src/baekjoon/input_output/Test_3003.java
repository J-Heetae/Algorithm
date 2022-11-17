package baekjoon.input_output;

import java.util.Scanner;

public class Test_3003 {
    public static void main(String[] args) {
        int[] set = {1, 1, 2, 2, 2, 8};
        int[] need = new int[6];

        Scanner sc = new Scanner(System.in);

        for(int i=0; i<6; i++) {
            need[i] = set[i] - sc.nextInt();
        }

        for(int i=0; i<6; i++) {
            if(i == 5) {
                System.out.print(need[i]);
            } else {
                System.out.print(need[i] + " ");
            }
        }
    }
}
