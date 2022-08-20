package String;

import java.util.Scanner;

//String_문자찾기
public class test220806_15 {
    public int solution(String str, char t) {
        int answer=0;
        str = str.toLowerCase();
        t = Character.toLowerCase(t);
//        for(int i=0; i<str.length(); i++) {
//            if(str.indexOf(i) == t) answer++;
//        }
        // .toCharArray() : String을 char로 나누어 배열로 만들어줌
        for(char s : str.toCharArray()) {
            if(s == t) answer++;
        }
        return answer;
    }

    public static void main(String[] args) {
        test220806_15 T = new test220806_15();
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        char c = sc.next().charAt(0);
        System.out.print(T.solution(str, c));
    }
}
