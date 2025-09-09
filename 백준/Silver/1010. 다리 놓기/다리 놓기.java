import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    static int[][] dp = new int[30][30];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < 30; i++) {
            dp[i][0] = 1; // nC0 = 1
            dp[i][i] = 1; // nCn = 1
            for (int j = 1; j < i; j++) {
                dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
            }
        }

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            sb.append(dp[M][N]).append("\n");
        }
        System.out.print(sb);
    }
}