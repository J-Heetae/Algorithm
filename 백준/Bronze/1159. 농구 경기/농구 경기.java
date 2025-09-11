import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            String lastName = br.readLine();
            cnt[lastName.charAt(0) - 'a']++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (cnt[i] >= 5) {
                sb.append((char) (i + 'a'));
            }
        }

        if (sb.isEmpty()) {
            System.out.println("PREDAJA");
        } else {
            System.out.println(sb);
        }
    }
}