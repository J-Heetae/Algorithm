import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine().trim();

        int[] cnt = new int[26];

        for (int i = 0; i < str.length(); i++) {
            cnt[str.charAt(i) - 'a']++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cnt.length; i++) {
            sb.append(cnt[i]).append(" ");
        }
        System.out.println(sb);
    }
}