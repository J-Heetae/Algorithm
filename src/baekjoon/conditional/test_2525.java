package baekjoon.conditional;

import java.util.Scanner;

public class test_2525 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int hour,min,cookMin,cookHour;

        hour = sc.nextInt();
        min = sc.nextInt();
        cookMin = sc.nextInt();
        cookHour = 0;

        if(cookMin > 60) {
            cookHour = cookMin / 60;
            cookMin = cookMin % 60;
        }

        hour += cookHour;
        min += cookMin;

        if(min > 59) {
            hour += min / 60;
            min = min % 60;
        }

        if(hour > 23) {
            hour -= 24;
        }

        System.out.print(hour + " " + min);
    }
}
