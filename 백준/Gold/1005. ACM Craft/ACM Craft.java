import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int[] timeArr;
    static int[] minTime;
    static int[] inDegree;
    static ArrayList<Integer>[] next;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testcases = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < testcases; tc++) {
            String[] line = br.readLine().split(" ");
            int N = Integer.parseInt(line[0]);
            int K = Integer.parseInt(line[1]);

            inDegree = new int[N + 1];
            timeArr = new int[N + 1];
            minTime = new int[N + 1];
            next = new ArrayList[N + 1];

            for (int i = 0; i <= N; i++) {
                next[i] = new ArrayList<>();
            }

            line = br.readLine().split(" ");
            for (int i = 1; i <= N; i++) {
                timeArr[i] = Integer.parseInt(line[i - 1]);
            }

            for (int i = 0; i < K; i++) {
                line = br.readLine().split(" ");
                int from = Integer.parseInt(line[0]);
                int to = Integer.parseInt(line[1]);

                inDegree[to]++;
                next[from].add(to);
            }

            int finalNum = Integer.parseInt(br.readLine());

            Queue<Integer> queue = new LinkedList<>();
            for (int i = 1; i <= N; i++) {
                if (inDegree[i] == 0) {
                    minTime[i] = timeArr[i];
                    queue.offer(i);
                }
            }

            while (!queue.isEmpty()) {
                int cur = queue.poll();

                if (cur == finalNum) {
                    bw.write(minTime[cur] + "\n");
                    break;
                }

                for (Integer next : next[cur]) {
                    inDegree[next]--;
                    minTime[next] = Math.max(minTime[next], timeArr[next] + minTime[cur]);
                    if (inDegree[next] == 0) {
                        queue.offer(next);
                    }
                }
            }
        }
        bw.flush();
    }
}