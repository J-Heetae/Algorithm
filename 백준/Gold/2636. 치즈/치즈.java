import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	static final int[] DX = {0,1,0,-1};
	static final int[] DY = {1,0,-1,0};

	static int row,col;

	static int[][] map;

	static boolean[][] isMelted, isChecked;
	static Queue<Coor> empty = new LinkedList<>();

	static PriorityQueue<Coor> cheese = new PriorityQueue<>(new Comparator<Coor>() {
		@Override
		public int compare(Coor o1, Coor o2) {
			return o1.time - o2.time;
		}
	});

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

		row = Integer.parseInt(tokenizer.nextToken());
		col = Integer.parseInt(tokenizer.nextToken());

		map = new int[row][col];

		for(int i=0; i<row; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for(int j=0; j<col; j++) {
				map[i][j] = Integer.parseInt(tokenizer.nextToken());
			}
		}

		isMelted = new boolean[row][col];
		isChecked = new boolean[row][col];

		checkEmpty(0, 0, 0);

		int max = Integer.MIN_VALUE;

		while (!cheese.isEmpty()) {
			Coor cur = cheese.poll();

			max = Math.max(max, cur.time);

			int nx,ny;
			for(int i=0; i<4; i++) {
				nx = cur.x + DX[i];
				ny = cur.y + DY[i];

				if(nx < 0 || ny < 0 || nx >= row || ny >= col) {
					continue;
				}

				if(map[nx][ny] == 1 && !isMelted[nx][ny]) {
					isMelted[nx][ny] = true;
					map[nx][ny] = cur.time + 1;
					cheese.offer(new Coor(nx, ny, cur.time + 1));
				} else if(map[nx][ny] == 0 && !isChecked[nx][ny]) {
					checkEmpty(nx, ny, cur.time);
				}
			}
		}
		System.out.println(max);
		int cnt = 0;
		for(int i=0; i<row; i++) {
			for(int j=0; j<col; j++) {
				if(map[i][j] == max) {
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}

	private static void checkEmpty(int x, int y, int time) {
		empty.offer(new Coor(x, y, time));
		isChecked[x][y] = true;
		while (!empty.isEmpty()) {
			Coor cur = empty.poll();

			int nx,ny;
			for(int i=0; i<4; i++) {
				nx = cur.x + DX[i];
				ny = cur.y + DY[i];

				if(nx < 0 || ny < 0 || nx >= row || ny >= col) {
					continue;
				}

				if(map[nx][ny] == 1 && !isMelted[nx][ny]) {
					isMelted[nx][ny] = true;
					map[nx][ny] = cur.time + 1;
					cheese.offer(new Coor(nx, ny, cur.time + 1));

				} else if(map[nx][ny] == 0 && !isChecked[nx][ny]){
					isChecked[nx][ny] = true;
					empty.offer(new Coor(nx, ny, cur.time));
				}
			}
		}
	}

	private static class Coor{
		int x,y,time;

		public Coor(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}
}