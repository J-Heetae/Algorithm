import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

class Main {

    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] read;

        read = br.readLine().split(" ");
        int n = Integer.parseInt(read[0]);
        int m = Integer.parseInt(read[1]);


        int[][] dist = new int[n + 1][n + 1];
        int[][] next = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            read = br.readLine().split(" ");

            int a = Integer.parseInt(read[0]);
            int b = Integer.parseInt(read[1]);
            int time = Integer.parseInt(read[2]);

            dist[a][b] = time;
            dist[b][a] = time;
            next[a][b] = b;
            next[b][a] = a;
        }

        for (int stopover = 1; stopover <= n; stopover++) {
            for (int from = 1; from <= n; from++) {
                for (int to = 1; to <= n; to++) {
                    if (dist[from][stopover] != INF && dist[stopover][to] != INF &&
                        dist[from][to] > dist[from][stopover] + dist[stopover][to]) {
                        dist[from][to] = dist[from][stopover] + dist[stopover][to];
                        next[from][to] = next[from][stopover];
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                bw.write((i == j) ? "- " : next[i][j] + " ");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}