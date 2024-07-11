import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

class Main {

    static final int INF = 987654321;

    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(tokenizer.nextToken());

        while (T-- > 0) {
            tokenizer = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(tokenizer.nextToken());
            int M = Integer.parseInt(tokenizer.nextToken());

            int[][] minDis = new int[N + 1][N + 1];
            for (int i = 1; i <= N; i++) {
                Arrays.fill(minDis[i], INF);
                minDis[i][i] = 0;
            }

            for (int i = 0; i < M; i++) {
                tokenizer = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(tokenizer.nextToken());
                int b = Integer.parseInt(tokenizer.nextToken());
                int c = Integer.parseInt(tokenizer.nextToken());

                minDis[a][b] = c;
                minDis[b][a] = c;
            }

            tokenizer = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(tokenizer.nextToken());

            Set<Integer> friends = new HashSet<>();
            tokenizer = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++) {
                friends.add(Integer.parseInt(tokenizer.nextToken()));
            }

            for (int k = 1; k <= N; k++) {
                for (int i = 1; i <= N; i++) {
                    for (int j = 1; j <= N; j++) {
                        if (minDis[i][j] > minDis[i][k] + minDis[k][j]) {
                            minDis[i][j] = minDis[i][k] + minDis[k][j];
                        }
                    }
                }
            }

            int bestRoomNumber = -1;
            int bestDistance = Integer.MAX_VALUE;
            for (int i = 1; i <= N; i++) {
                int curDistance = 0;
                for (Integer friend : friends) {
                    curDistance += minDis[friend][i];
                }

                if (curDistance < bestDistance) {
                    bestDistance = curDistance;
                    bestRoomNumber = i;
                }
            }
            bw.write(bestRoomNumber + "\n");
        }
        bw.flush();
    }
}