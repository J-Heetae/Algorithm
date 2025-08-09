import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Integer> counts = new TreeMap<>();

        int N = Integer.parseInt(br.readLine().trim());
        for (int i = 0; i < N; i++) {
            String file = br.readLine().trim();
            int dot = file.indexOf('.');
            String ext = file.substring(dot + 1);
            counts.merge(ext, 1, Integer::sum);
        }

        StringBuilder out = new StringBuilder();
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            out.append(entry.getKey()).append(" ").append(entry.getValue()).append("\n");
        }
        System.out.print(out);
    }

}