import java.util.*;

//String_특정문자뒤집기
public class test220807_19 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        char[] c = str.toCharArray();
        int lt = 0, rt = str.length() - 1;

        while (lt < rt) {
            if(!Character.isAlphabetic(c[lt])) lt++;
            else if(!Character.isAlphabetic(c[rt])) rt--;
            else {
                char temp = c[lt];
                c[lt] = c[rt];
                c[rt] = temp;
                lt++;
                rt--;
            }
        }
        System.out.println(String.valueOf(c));

    }
}
