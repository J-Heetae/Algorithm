import java.util.*;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		int[][] items = new int[N + 1][2];
		for(int i=1; i<=N; i++) {
			items[i][0] = sc.nextInt();
			items[i][1] = sc.nextInt();
		}
		
		int[][] dp = new int[N + 1][K + 1];
		for(int idx=1; idx<=N; idx++) {
			int[] currItem = items[idx];
			for(int weight=1; weight<=K; weight++) {
				if(weight < currItem[0]) {
					dp[idx][weight] = Math.max(dp[idx][weight - 1], dp[idx - 1][weight]);
				} else {
					dp[idx][weight] = Math.max(dp[idx - 1][weight - currItem[0]] + currItem[1], dp[idx - 1][weight]);
				}
			}
		}
		System.out.println(dp[N][K]);
	}
}