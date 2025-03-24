import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static Scanner sc = new Scanner(System.in);

	static final int[] DX = { -1, 0, 0, 1 };
	static final int[] DY = { 0, -1, 1, 0 };

	// manStatus
	static final int WAIT = 0;
	static final int ON = 1;
	static final int END = 2;

	static int n, m, arrival, time;
	static int[] manX, manY, manStatus;
	static int[] destX, destY;
	static int[][] map; // 0:이동 가능, 1:사용 가능 베이스, 2:이동 불가

	public static void main(String[] args) {
		init();
		while (arrival < m) {
			time++;
			step1();
			step2();
			step3();
		}
		System.out.println(time);
	}

	static void step1() {
		for (int i = 1; i <= m; i++) {
			if (manStatus[i] == ON) {
				move(i);
			}
		}
	}

	static void move(int manIdx) {
		int minDist = Integer.MAX_VALUE;
		int dir = 0;

		for (int i = 0; i < 4; i++) {
			int nx = manX[manIdx] + DX[i];
			int ny = manY[manIdx] + DY[i];

			if (nx <= 0 || nx > n || ny <= 0 || ny > n) { // 맵을 벗어난다면
				continue;
			}

			if (map[nx][ny] == 2) { // 이동할 수 없는 위치라면
				continue;
			}

			int currMinDist = getMinDist(nx, ny, manIdx);
			if (minDist > currMinDist) {
				minDist = currMinDist;
				dir = i;
			}
		}

		// 최적의 방향으로 이동
		manX[manIdx] += DX[dir];
		manY[manIdx] += DY[dir];
	}

	static int getMinDist(int x, int y, int manIdx) {
		boolean[][] visited = new boolean[n + 1][n + 1];

		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] { x, y, 1 });
		visited[x][y] = true;

		while (!que.isEmpty()) {
			int[] poll = que.poll();

			if (poll[0] == destX[manIdx] && poll[1] == destY[manIdx]) { // 도착했으면
				return poll[2];
			}

			for (int i = 0; i < 4; i++) {
				int nx = poll[0] + DX[i];
				int ny = poll[1] + DY[i];

				if (nx <= 0 || nx > n || ny <= 0 || ny > n) { // 맵을 벗어난다면
					continue;
				}

				if (map[nx][ny] == 2 || visited[nx][ny]) { // 이동할 수 없는 위치라면
					continue;
				}

				visited[nx][ny] = true;
				que.offer(new int[] { nx, ny, poll[2] + 1 });
			}
		}
		return Integer.MAX_VALUE;
	}

	static void step2() {
		for (int i = 1; i <= m; i++) {
			if (manStatus[i] == ON) {
				arrive(i);
			}
		}
	}

	static void arrive(int manIdx) {
		if (manX[manIdx] == destX[manIdx] && manY[manIdx] == destY[manIdx]) { // 편의점 도착
			manStatus[manIdx] = END;
			map[manX[manIdx]][manY[manIdx]] = 2;
			arrival++;
		}
	}

	static void step3() {
		if (time <= m) {
			manStatus[time] = ON;

			int[] base = getBase(time);

			manX[time] = base[0];
			manY[time] = base[1];

			map[base[0]][base[1]] = 2;
		}
	}

	static int[] getBase(int manIdx) {
		boolean[][] visited = new boolean[n + 1][n + 1];

		int minDis = Integer.MAX_VALUE;

		ArrayList<int[]> list = new ArrayList<>(); 

		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] { destX[manIdx], destY[manIdx], 0});
		visited[destX[manIdx]][destY[manIdx]] = true;

		while (!que.isEmpty()) {
			int[] poll = que.poll();

			if(poll[2] > minDis) {
				break;
			}

			if (map[poll[0]][poll[1]] == 1) { // 베이스캠프 도착
				list.add(new int[] {poll[0], poll[1]});
				minDis = poll[2];
				continue;
			}

			for (int i = 0; i < 4; i++) {
				int nx = poll[0] + DX[i];
				int ny = poll[1] + DY[i];

				if (nx <= 0 || nx > n || ny <= 0 || ny > n) { // 맵을 벗어난다면
					continue;
				}

				if (map[nx][ny] == 2 || visited[nx][ny]) { // 이동할 수 없는 위치라면
					continue;
				}

				visited[nx][ny] = true;
				que.offer(new int[] { nx, ny, poll[2] + 1 });
			}
		}
		
		Collections.sort(list, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0] == o2[0]) {
					return o1[1] - o2[1];
				}
				return o1[0] - o2[0];
			}
		});
		return list.get(0);
	}

	static void init() {
		n = sc.nextInt();
		m = sc.nextInt();

		manX = new int[m + 1];
		manY = new int[m + 1];
		manStatus = new int[m + 1];

		destX = new int[m + 1];
		destY = new int[m + 1];

		map = new int[n + 1][n + 1];

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		for (int i = 1; i <= m; i++) {
			destX[i] = sc.nextInt();
			destY[i] = sc.nextInt();
		}
	}
}