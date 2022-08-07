import java.util.*;

//String_단어뒤집기
public class test220807_18 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        String[] words = new String[num];

        for(int i= 0; i< num; i++) {
            words[i] = sc.next();
        }

        /*//StringBuilder사용
        ArrayList<String> answer = new ArrayList<>();
        for (String word : words) {
            answer.add(new StringBuilder(word).reverse().toString());
        }

        for (String s : answer) {
            System.out.println(s);
        }*/

        //lt rt 이용
        ArrayList<String> answer = new ArrayList<>();
        for (String word : words) {
            char[] c = word.toCharArray();
            int lt = 0, rt = word.length() - 1;
            while(lt<rt) {
                char tmp = c[lt];
                c[lt] = c[rt];
                c[rt] = tmp;
                lt++;
                rt--;
            }
            answer.add(String.valueOf(c));
        }

        for(String s : answer) {
            System.out.println(s);
        }
        
        
        /*for (int i = 0; i < words.length; i++) {
            int len = words[i].length();
            System.out.println("len = " + len);
            String temp = "";
            for(int j=0; j < len; j++) {
                temp += words[i].charAt((len-1) - j);
            }
            words[i] = temp;
        }

        for (String word : words) {
            System.out.println(word);
        }*/
        
        
    }
}
