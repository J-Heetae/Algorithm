package baekjoon.string;

import java.util.Scanner;

public class Test_10809 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();

        char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
                'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        int length = alphabet.length;
        int[] check = new int[length];

        for(int i=0; i<length; i++) {
            check[i] = -1;
        }

        for(int i=0; i<str.length(); i++) {
            for (int j = 0; j < length; j++) {
                if (str.charAt(i) == alphabet[j]) {
                    if(check[j] != -1) {
                        if(check[j] > i) check[j] = i;
                    } else {
                        check[j] = i;
                    }
                }
            }
        }

        for (int i : check) {
            System.out.print(i + " ");
        }

    }
}
