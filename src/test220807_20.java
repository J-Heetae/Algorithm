import java.util.*;

//String_중복문자제거
public class test220807_20 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        String answer = "";

        for(int i=0; i<str.length(); i++) {
            if(str.indexOf(str.charAt(i)) == i) answer += str.charAt(i);
        }

        System.out.println(answer);
    }
}