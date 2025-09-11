import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] pattern = br.readLine().split("\\*");

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < N; i++) {
            String str = br.readLine();

            boolean flag = true;
            if (str.length() < pattern[0].length() + pattern[1].length()) {
                flag = false;
            } else {
                for (int j = 0; j < pattern[0].length(); j++) {
                    if (str.charAt(j) != pattern[0].charAt(j)) {
                        flag = false;
                        break;
                    }
                }
                for (int j = 0; j < pattern[1].length(); j++) {
                    if (str.charAt(str.length() - pattern[1].length() + j) != pattern[1].charAt(j)) {
                        flag = false;
                        break;
                    }
                }
            }
            if (flag) {
                answer.append("DA");
            } else {
                answer.append("NE");
            }
            answer.append("\n");
        }
        System.out.println(answer);
    }
}