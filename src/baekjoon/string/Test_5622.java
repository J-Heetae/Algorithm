package baekjoon.string;

import java.util.Scanner;

public class Test_5622 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int time = 0;

        for(char c : str.toCharArray()) {
            int num = (int) c;

            if(num < 68) time += 3;
            else if(num < 71) time += 4;
            else if(num < 74) time += 5;
            else if(num < 77) time += 6;
            else if(num < 80) time += 7;
            else if(num < 84) time += 8;
            else if(num < 87) time += 9;
            else time += 10;
        }

        System.out.println(time);
    }
}
