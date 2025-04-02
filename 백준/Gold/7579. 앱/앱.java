import java.util.*;

class Main {
	
	static int N, M;
	
	static int minTime = Integer.MAX_VALUE;
	static ArrayList<int[]> list = new ArrayList<>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		int[] size = new int[N];
		int[] time = new int[N];
		
		for(int i=0; i<N; i++) {
			size[i] = sc.nextInt();
		}
		
		int worstTime = 0;
		for(int i=0; i<N; i++) {
			time[i] = sc.nextInt();
			worstTime += time[i];
		}
		
		int[] dp = new int[worstTime + 1];
		for(int i=0; i<N; i++) {		
			for(int j=worstTime; j>=time[i]; j--) {
				dp[j] = Math.max(dp[j], dp[j - time[i]] + size[i]);
			}
		}
		
		int minTime = 0;
		for(int i=1; i<=worstTime; i++) {
			if(dp[i] >= M) {
				minTime = i;
				break;
			}
		}
		System.out.print(minTime);
	}
}