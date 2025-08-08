import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Integer> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        
        int N = Integer.parseInt(br.readLine().trim());
        for(int i=0; i<N; i++) {
            String file = br.readLine().trim();
            String[] split = file.split("\\.");
            String extension = split[1];

            if(!map.containsKey(extension)) {
                map.put(extension, 1);
                list.add(extension);
            } else {
                map.put(extension, map.get(extension) + 1);
            }
        }

        list.sort(null);
        StringBuilder out = new StringBuilder();
        for (String extension : list) {
            out.append(extension).append(" ").append(map.get(extension)).append("\n");
        }
        System.out.print(out);
    }

}