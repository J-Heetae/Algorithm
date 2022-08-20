package String;

import java.util.Scanner;

//String_대소문자변환
public class test220806_16 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        String answer = "";

        /*for (int i = 0; i < str.length(); i++) {
            char s = str.charAt(i);
            if(Character.isUpperCase(s))
                answer += Character.toLowerCase(s);
            else
                answer += Character.toUpperCase(s);
        }*/

        /*for (char c : str.toCharArray()) {
            if(Character.isUpperCase(c))
                answer += Character.toLowerCase(c);
            else
                answer += Character.toUpperCase(c);
        }*/

        //ASCII 아스키코드 이용
        for (char c : str.toCharArray()) {
            if(c>=97 && c<=122)
                answer += (char)(c - 32);
            else
                answer += (char)(c + 32);
        }

       System.out.print(answer);
    }
}
