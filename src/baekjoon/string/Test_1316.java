package baekjoon.string;

import java.util.ArrayList;
import java.util.Scanner;

public class Test_1316 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int cnt = 0;

        for(int i=0; i<num; i++) {
            String word = sc.next();
            boolean flag = true;
            Character back = ' ';
            ArrayList<Character> words = new ArrayList<>();

            for(Character c : word.toCharArray()) {
                if(!words.contains(c)) {
                    words.add(c);
                    back = c;
                } else {
                    if (back != c) {
                        flag = false;
                        break;
                    }
                }
            }
            if(flag) cnt++;
        }

        System.out.print(cnt);
    }
}
