import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

class Main {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokenizer;

    public static void main(String[] args) throws IOException {
        Set<Integer> set = new HashSet<>();
        StringBuilder stringBuilder = new StringBuilder();

        int N = Integer.parseInt(reader.readLine());
        tokenizer = new StringTokenizer(reader.readLine());
        while (tokenizer.hasMoreTokens()) {
            set.add(Integer.parseInt(tokenizer.nextToken()));
        }

        int M = Integer.parseInt(reader.readLine());
        tokenizer = new StringTokenizer(reader.readLine());
        while (tokenizer.hasMoreTokens()) {
            stringBuilder.append((set.contains(Integer.parseInt(tokenizer.nextToken()))) ? 1 : 0)
                         .append(" ");
        }
        System.out.println(stringBuilder);
    }
}