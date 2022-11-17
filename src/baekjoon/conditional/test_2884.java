package baekjoon.conditional;

import java.util.Scanner;

public class test_2884 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int hour,min;

        hour = sc.nextInt();
        min = sc.nextInt() - 45;

        if(min < 0) {
            hour -= 1;
            min = 60 + min;
        }

        if(hour < 0) hour = 23;

        System.out.print(hour + " " + min);
    }
}
