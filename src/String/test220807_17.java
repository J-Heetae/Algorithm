package String;

import java.util.Scanner;

//String_문장속단어
public class test220807_17 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        /*String[] arr = str.split(" ");
        String answer = "";
        int maxLength = Integer.MIN_VALUE;

        for (String s : arr) {
            if(s.length() > maxLength) {
                maxLength = s.length();
                answer = s;
            }
        }*/

        //indexof, substring 사용하기기
        String answer = "";
        int m = Integer.MIN_VALUE, pos;

        while((pos=str.indexOf(" ")) != -1) {
            String tmp = str.substring(0, pos);
            int len = tmp.length();
            if(len>m) {
                m = len;
                answer = tmp;
            }
            str = str.substring(pos+1);
        }

        if(str.length()>m) answer = str;

        System.out.println(answer);
    }
}
