package baekjoon.string;

import java.util.Scanner;

public class Test_2941 {
    public static void main(String[] args) {
        String[] croatia = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};

        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int cnt = 0;

        while(str.length() > 0) {
            boolean flag = false;

            for(String s : croatia) {
                if (str.startsWith(s)) {
                    flag = true;
                    cnt++;
                    str = str.substring(s.length());
                    break;
                }
            }

            if(!flag) {
                cnt++;
                str = str.substring(1);
            }
        }

        System.out.print(cnt);
    }
}
