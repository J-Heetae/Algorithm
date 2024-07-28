import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokenizer;

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        HashMap<String, Integer> map = new HashMap<>();
        for(int i=0; i<N; i++) {
            map.put(reader.readLine(), 1);
        }

        ArrayList<String> answer = new ArrayList<>();
        for(int i=0; i<M; i++) {
            String name = reader.readLine();
            if(map.containsKey(name)) {
                answer.add(name);
            }
        }

        Collections.sort(answer);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(answer.size()).append("\n");
        for (String s : answer) {
            stringBuilder.append(s).append("\n");
        }
        System.out.println(stringBuilder);
    }
}