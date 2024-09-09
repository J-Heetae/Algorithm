import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[][] matrix = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            matrix[i][0] = Integer.parseInt(st.nextToken());
            matrix[i][1] = Integer.parseInt(st.nextToken());
        }
        int[][] dp = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            dp[i][i] = 0;
        }
        for (int size = 1; size < N; size++) {
            for (int from = 1; from + size <= N; from++) {
                for (int div = 0; div < size; div++) {
                    dp[from][from + size] = Math.min(dp[from][from + size],
                        dp[from][from + div] + dp[from + div + 1][from + size] +
                            matrix[from][0] * matrix[from + div][1] * matrix[from + size][1]);
                }
            }
        }
        System.out.println(dp[1][N]);
    }
}