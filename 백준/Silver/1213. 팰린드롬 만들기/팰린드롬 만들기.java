import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    final private static String FAIL = "I'm Sorry Hansoo";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int[] count = new int[26];

        for (char c : str.toCharArray()) count[c - 'A']++;

        int odd = 0;
        char center = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                odd++;
                center = (char) ('A' + i);
            }
        }

        if (odd > (str.length() % 2)) {
            System.out.println("I'm Sorry Hansoo");
            return;
        }

        StringBuilder half = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < count[i] / 2; j++) half.append((char) ('A' + i));
        }

        String result = half.toString();
        StringBuilder sb = new StringBuilder(result);
        if (str.length() % 2 == 1) sb.append(center);
        sb.append(new StringBuilder(result).reverse());

        System.out.println(sb);
    }
}