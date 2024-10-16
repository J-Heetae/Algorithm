import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();

        while(testCases-- > 0) {
            int coinType = sc.nextInt();
            int[] coins = new int[coinType + 1];
            for(int i=1; i<=coinType; i++) {
                coins[i] = sc.nextInt();
            }
            int targetPrice = sc.nextInt();

            int[][] dp = new int[coinType + 1][targetPrice + 1];

            for(int i=1; i<=coinType; i++) {
                dp[i][coins[i]]++;
            }

            for(int i=1; i<=coinType; i++) {
                for(int j=1; j<=targetPrice; j++) {
                    if(j >= coins[i]) {
                        dp[i][j] += dp[i - 1][j] + dp[i][j - coins[i]];
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
            System.out.println(dp[coinType][targetPrice]);
        }
    }
}