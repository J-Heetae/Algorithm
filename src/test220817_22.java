import java.util.*;

//String 가장 짧은 문자거리
public class test220817_22 {
    public static void main(String[] arg) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        char c = sc.next().charAt(0);
        int[] answer = new int[str.length()];
        int p = 1000;

        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == c) {
                p = 0;
                answer[i] = p;
            } else {
                p++;
                answer[i] = p;
            }
        }

        p = 1000;

        for(int i = str.length() - 1; i >= 0; i--) {
            if(str.charAt(i) == c) {
                p = 0;
            } else {
                p++;
                //if(answer[i] > p) answer[i] = p;
                // Math.min 사용
                answer[i] = Math.min(answer[i], p);
            }
        }

        for(int i =0; i<answer.length; i++) {
            if(i == answer.length)
                System.out.print(answer[i]);
            else
                System.out.print(answer[i] + " ");
        }
    }
}
