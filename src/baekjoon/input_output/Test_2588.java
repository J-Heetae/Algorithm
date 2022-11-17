package baekjoon.input_output;

import java.util.Scanner;

public class Test_2588 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a, b;
        a = sc.nextInt();
        b = sc.nextInt();
        int[] digit = new int[4];

        String str = String.valueOf(b);
        int length = str.length();

        int cnt = length - 1;
        for(char c : str.toCharArray()) {
            digit[cnt--] = Character.getNumericValue(c);
        }

        digit[length] = b;

        for (int i : digit) {
            System.out.println(a * i);
        }

    }
}
