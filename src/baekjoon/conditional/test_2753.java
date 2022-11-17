package baekjoon.conditional;

import java.util.Scanner;

public class test_2753 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int year = sc.nextInt();
        int leapYear = 0;

        if(year%4 == 0 && year%100 != 0) {
            leapYear = 1;
        } else if (year%400 == 0) {
            leapYear = 1;
        }

        System.out.println(leapYear);
    }
}
