import java.util.*;

//String 암호
public class test220818_24 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        String str = sc.next().replace("#", "1").replace("*","0");
        String answer = "";

        /*for(int i=0; i < num; i++) {
            String tmp = str.substring(0, 7);
            str = str.substring(7);
            int tmpNum = Integer.parseInt(tmp, 2);
            char aski = (char)tmpNum;
            answer += aski + "";
        }*/

        for(int i=0; i < num; i++) {
            answer += (char)Integer.parseInt(str.substring(0, 7), 2);
            str = str.substring(7);
        }

        System.out.print(answer);
    }
}
