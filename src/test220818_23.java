import java.util.*;

//String 문자열 압축
public class test220818_23 {
    // i와 i-1 비교
/*    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        StringBuilder answer = new StringBuilder(str.charAt(0) + "");
        int cnt = 1;

        for(int i =1; i<str.length(); i++) {
            if (str.charAt(i) == str.charAt(i - 1)) {
                cnt++;
                if(i == str.length() - 1)
                    answer.append(cnt);
            } else {
                if (cnt > 1) {
                    answer.append(cnt);
                    cnt = 1;
                }
                answer.append(str.charAt(i));
            }
        }

        System.out.print(answer);
    }*/
    //i와 i+1 비교
    /*public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        StringBuilder answer = new StringBuilder();
        int cnt = 1;

        for(int i =0; i<str.length(); i++) {
            if(i+1 !=str.length()) {
                if (str.charAt(i) == str.charAt(i + 1)) {
                    cnt++;
                } else {
                    answer.append(str.charAt(i));
                    if (cnt > 1) {
                        answer.append(cnt);
                    }
                    cnt = 1;
                }
            } else {
                if(str.charAt(i) == str.charAt(i-1)) {
                    answer.append(str.charAt(i)).append(cnt);

                } else {
                    answer.append(str.charAt(i));
                }
            }
        }
        System.out.print(answer);
    }*/

    //i와 i+1비교 빈문자열 추가
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine() + " ";
        String answer = "";
        int cnt = 1;

        for (int i = 0; i < str.length() - 1; i++) {
            if(str.charAt(i) == str.charAt(i+1)) cnt++;
            else {
                answer += str.charAt(i);
                if(cnt > 1) {
                    answer += String.valueOf(cnt);
                    cnt = 1;
                }
            }
        }

        System.out.print(answer);
    }
}
