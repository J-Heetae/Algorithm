import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] items = new int[N][2];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            items[i][0] = Integer.parseInt(st.nextToken());
            items[i][1] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N + 1][K + 1];
        for(int i=1; i<=N; i++) {
            int w = items[i-1][0];
            int v = items[i-1][1];
            for(int j=1; j<=K; j++) {
                dp[i][j] = dp[i - 1][j];
                if(j - w >= 0) {
                    dp[i][j] = Math.max(dp[i - 1][j - w] + v, dp[i][j]);
                }
            }
        }

        System.out.print(dp[N][K]);
    }
}