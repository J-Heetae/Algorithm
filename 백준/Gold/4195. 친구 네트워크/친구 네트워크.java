import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;

public class Main {

    static HashMap<String, Integer> nameIdxMap;
    static int[] group = new int[200_001];
    static int[] groupSize = new int[200_001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tc = Integer.parseInt(br.readLine());
        for (int i = 0; i < tc; i++) {
            nameIdxMap = new HashMap<>();
            Arrays.fill(groupSize, 1);
            for (int j = 0; j <= 200_000; j++) {
                group[j] = j;
            }

            int network = Integer.parseInt(br.readLine());
            for (int j = 0; j < network; j++) {
                String[] names = br.readLine().split(" ");

                if (!nameIdxMap.containsKey(names[0]))
                    nameIdxMap.put(names[0], nameIdxMap.size());

                if (!nameIdxMap.containsKey(names[1]))
                    nameIdxMap.put(names[1], nameIdxMap.size());

                union(find(nameIdxMap.get(names[0])), find(nameIdxMap.get(names[1])));
                int idx = find(nameIdxMap.get(names[0]));
                bw.append(groupSize[idx] + "\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static int find(int idx) {
        if(idx == group[idx]) return idx;
        return group[idx] = find(group[idx]);
    }

    static void union(int idx1, int idx2) {
        int group1 = find(idx1);
        int group2 = find(idx2);

        if(group1 != group2) {
            if(groupSize[group1] <= groupSize[group2]) {
                group[group1] = group2;
                groupSize[group2] += groupSize[group1];
                groupSize[group1] = 0;
            } else {
                group[group2] = group1;
                groupSize[group1] += groupSize[group2];
                groupSize[group2] = 0;
            }
        }
    }
}