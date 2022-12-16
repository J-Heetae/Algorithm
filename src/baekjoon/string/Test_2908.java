package baekjoon.string;

import java.util.Scanner;

public class Test_2908 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String num1 = sc.next();
        String num2 = sc.next();

        String reverseNum1 = reverseNum(num1);
        String reverseNum2 = reverseNum(num2);

        if(Integer.parseInt(reverseNum1) >
        Integer.parseInt(reverseNum2)) {
            System.out.println(Integer.parseInt(reverseNum1));
        } else {
            System.out.println(Integer.parseInt(reverseNum2));
        }

    }

    private static String reverseNum(String str) {
        char tmp1 = str.charAt(0);
        char tmp2 = str.charAt(1);
        char tmp3 = str.charAt(2);

        String reverseNum = String.valueOf(tmp3) + String.valueOf(tmp2) + String.valueOf(tmp1);
        return reverseNum;
    }
}
