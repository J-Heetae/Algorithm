import java.util.*;

class Main {
	
	static int[][] list = new int[101][101];
	static boolean[] middle = new boolean[101]; 
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		for(int i=0; i<M; i++) {
			int X = sc.nextInt();
			int Y = sc.nextInt();
			int K = sc.nextInt();
			middle[X] = true;
			list[X][Y] = K;
		}
		
		explore(N);
		
		StringBuilder result = new StringBuilder();
		for(int i=1; i<=100; i++) {
			if(list[N][i] != 0) {
				result.append(i).append(" ").append(list[N][i]).append("\n");
			}
		}
		System.out.print(result);
 	}
	
	static void explore(int num) {
		for(int i=1; i<=100; i++) {
			if(list[num][i] != 0 && middle[i]) {
				explore(i);
				int count = list[num][i];
				list[num][i] = 0;
				for(int j=1; j<=100; j++) {
					list[num][j] += list[i][j] * count;
				}
			}
		}
	}
}