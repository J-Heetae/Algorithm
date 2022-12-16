package baekjoon.string;

import java.util.Scanner;

public class Test_1152 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int cnt = 1;

        for (char c : str.toCharArray()) {
            if (c == ' ') cnt++;
        }

        if (str.charAt(0) == ' ') cnt--;
        if (str.charAt(str.length()-1) == ' ') cnt--;

        System.out.println(cnt);
    }
}
