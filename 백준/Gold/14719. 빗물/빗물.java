import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int col = Integer.parseInt(st.nextToken());
		int row = Integer.parseInt(st.nextToken());
		int[][] map = new int[row][col];

		st = new StringTokenizer(br.readLine());
		for(int i=0; i<row; i++) {
			int cur = Integer.parseInt(st.nextToken());
			for(int j=0; j<cur; j++) {
				map[i][j] = 1;
			}
		}
		int cnt = 0;
		for(int i=0; i<col; i++) {
			for(int j=0; j<row; j++) {
				if(map[j][i] == 1) {
					int tmp = 0;
					j++;
					while (j < row) {
						if(map[j][i] == 1) {
							cnt += tmp;
							break;
						}
						tmp++;
						j++;
					}
					j--;
				}
			}
		}
		System.out.println(cnt);
	}
}