import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(reader.readLine());
        StringBuilder answer = new StringBuilder();

        while (T-- > 0) {
            int n = Integer.parseInt(reader.readLine());

            if (n == 0) {
                answer.append(0).append("\n");
                continue;
            }

            Map<String, Integer> categoryCount = new HashMap<>();

            for (int i = 0; i < n; i++) {
                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                String item = tokenizer.nextToken();
                String category = tokenizer.nextToken();

                categoryCount.put(category, categoryCount.getOrDefault(category, 0) + 1);
            }

            int combinations = 1;

            for (int count : categoryCount.values()) {
                combinations *= (count + 1); // 각 카테고리별로 선택하지 않는 경우를 포함
            }

            answer.append(combinations - 1).append("\n"); // 아무것도 선택하지 않는 경우 제외
        }

        System.out.println(answer);
    }
}
