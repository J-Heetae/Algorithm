import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] read;

        read = br.readLine().split(" ");
        int n = Integer.parseInt(read[0]);
        int k = Integer.parseInt(read[1]);

        int[] coins = new int[n];
        for(int i = 0; i < n; i++) {
            read = br.readLine().split(" ");
            coins[i] = Integer.parseInt(read[0]);
        }

        int[][] dp = new int[n][k + 1];
        for(int i=0; i<n; i++) {
            dp[i][0] = 1;
        }

        for(int i=0; i<n; i++) {
            for(int j=1; j<=k; j++) {
                if(i != 0) {
                    dp[i][j] += dp[i - 1][j];
                }

                if(j >= coins[i]) {
                    dp[i][j] += dp[i][j - coins[i]];
                }
            }
        }

        System.out.println(dp[n - 1][k]);
    }

}