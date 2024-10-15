import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int unitNumber = sc.nextInt();
        int totalTime = sc.nextInt();

        int[] timeArr = new int[unitNumber + 1];
        int[] scoreArr = new int[unitNumber + 1];

        for (int i = 1; i <= unitNumber; i++) {
            timeArr[i] = sc.nextInt();
            scoreArr[i] = sc.nextInt();
        }

        int[][] dp = new int[unitNumber + 1][totalTime + 1];
        for (int u = 1; u <= unitNumber; u++) {
            for (int t = 1; t <= totalTime; t++) {
                if(t - timeArr[u] >= 0) {
                    dp[u][t] = Math.max(dp[u - 1][t], dp[u - 1][t - timeArr[u]] + scoreArr[u]);
                } else {
                    dp[u][t] = dp[u - 1][t];
                }
            }
        }
        System.out.println(dp[unitNumber][totalTime]);
    }
}