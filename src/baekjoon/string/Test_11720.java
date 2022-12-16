package baekjoon.string;

import java.util.Scanner;

public class Test_11720 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        String nums = sc.next();
        int sum = 0;

        for(int i=0; i<size; i++) {
            sum += nums.charAt(i) - 48;
        }

        System.out.println(sum);
    }
}
