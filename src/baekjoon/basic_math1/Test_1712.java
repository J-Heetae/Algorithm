package baekjoon.basic_math1;

import java.util.Scanner;

public class Test_1712 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int fixed = sc.nextInt();
        int variable = sc.nextInt();
        int price = sc.nextInt();

        if (variable >= price) System.out.println(-1);
        else {
            int profit = price - variable;
            int sales = fixed / profit;
            System.out.println(sales + 1);
        }
    }
}
