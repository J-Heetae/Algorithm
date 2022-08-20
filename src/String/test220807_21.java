package String;

import java.util.*;

//String_회문문자열
public class test220807_21 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next().toLowerCase();

        int lt = 0, rt = str.length() - 1;
        boolean flag = true;
        while(lt<rt) {
            if (str.charAt(lt) == str.charAt(rt)) {
                lt++;
                rt--;
            } else {
                System.out.println("NO");
                flag = false;
                break;
            }
        }
        if(flag) System.out.println("YES");
    }
}
