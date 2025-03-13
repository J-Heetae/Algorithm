import java.io.*;
import java.util.*;

public class Main {
	
	static final int[] DX = {0, 1, 1, 1, 0, -1, -1, -1};
	static final int[] DY = {1, 1, 0, -1, -1, -1, 0, 1};
	
	static int N, M, K, currTurn;
	static Cannon[][] map;
	
	public static class Cannon implements Comparable<Cannon>{
		int x, y;
		int power;
		int lastAttack;
		boolean repair;
		
		public Cannon(int x, int y, int power) {
			this.x = x;
			this.y = y;
			this.power = power;
			this.lastAttack = 0;
			this.repair = true;
		}

		@Override
		public int compareTo(Cannon o) {
			if(this.power != o.power) return this.power - o.power;
			if(this.lastAttack != o.lastAttack) return o.lastAttack - this.lastAttack;
			if((this.x + this.y) != (o.x + o.y)) return (o.x + o.y) - (this.x + this.y);	
			return o.y - this.y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] read = br.readLine().split(" ");
		N = Integer.parseInt(read[0]);
		M = Integer.parseInt(read[1]);
		K = Integer.parseInt(read[2]);
		
		map = new Cannon[N][M];
		
		for(int i=0; i<N; i++) {
			read = br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				int power = Integer.parseInt(read[j]);
				map[i][j] = new Cannon(i, j, power);
			}
		}
		
		while(currTurn < K) {
			currTurn++;
			
			ArrayList<Cannon> list = new ArrayList<>();
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j].power > 0) {
						list.add(map[i][j]);
					}
				}
			}
			
			if(list.size() <= 1) {
				break;
			}
			
			Collections.sort(list);
			
			int[] attacker = new int[] {list.get(0).x, list.get(0).y};
			int[] defender = new int[] {list.get(list.size() - 1).x, list.get(list.size() - 1).y};
			
			map[attacker[0]][attacker[1]].power += N + M;
			map[attacker[0]][attacker[1]].lastAttack = currTurn;
			map[attacker[0]][attacker[1]].repair = false;
			
			attack(attacker, defender);
			repairCannon();
		}
		
		System.out.print(getMaxPower());
	}
	
	public static int getMaxPower() {
		int maxPower = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				maxPower = Math.max(maxPower, map[i][j].power);
			}
		}
		return maxPower;
	}

	public static void attack(int[] attacker, int[] defender) {
		int damage = map[attacker[0]][attacker[1]].power;
		
		int[][][] path = lazer(attacker, defender);
		
		map[defender[0]][defender[1]].power = Math.max(0, map[defender[0]][defender[1]].power - damage);	
		map[defender[0]][defender[1]].repair = false;
		
		if(path != null) {
			int x = path[defender[0]][defender[1]][0];
			int y = path[defender[0]][defender[1]][1];
			
			while(!(x == attacker[0] && y == attacker[1])) {
				map[x][y].power = Math.max(0, map[x][y].power - damage/2);
				map[x][y].repair = false;
				
				int nx = path[x][y][0];
				int ny = path[x][y][1];
				
				x = nx;
				y = ny;
			}
			return;
		}

		//포탄 공격
		for(int i=0; i<8; i++) {
			int nx = (defender[0] + DX[i] + N) % N;
			int ny = (defender[1] + DY[i] + M) % M;
			
			if(nx == attacker[0] && ny == attacker[1]) { //공격자인 경우 넘어가기
				continue;
			}
			
			map[nx][ny].power = Math.max(0, map[nx][ny].power - damage/2);
			map[nx][ny].repair = false;
		}
	}
	
	public static int[][][] lazer(int[] attacker, int[] defender) {
		int[][][] path = new int[N][M][2];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				path[i][j][0] = -1;
				path[i][j][1] = -1;
			}
		}
		
		Queue<int[]> que = new LinkedList<>();
		que.offer(attacker);
		path[attacker[0]][attacker[1]][0] = attacker[0];
		path[attacker[0]][attacker[1]][1] = attacker[1];
		
		while(!que.isEmpty()) {
			int[] curr = que.poll();
			
			//도착한경우
			if(curr[0] == defender[0] && curr[1] == defender[1]) {
				break;
			}
			
			for(int i=0; i<8; i+=2) {
				int nx = (curr[0] + DX[i] + N) % N;
				int ny = (curr[1] + DY[i] + M) % M;
				
				if(map[nx][ny].power == 0) { //부서진 포탑 넘어가기
					continue;
				}
				
				if(path[nx][ny][0] != -1) { //최단 경로가 아닌 경우 넘어가기
					continue;
				}
				
				//이전 경로 기록하기
				path[nx][ny][0] = curr[0];
				path[nx][ny][1] = curr[1];
				
				que.offer(new int[]{nx, ny});
			}
				
		}
		
		if(path[defender[0]][defender[1]][0] != -1) {
			return path;
		}
		return null;
	}
	
	public static void repairCannon() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j].power == 0) {
					continue;
				}
				
				if(map[i][j].repair) {
					map[i][j].power += 1;
				} else {
					map[i][j].repair = true;
				}
			}
		}
	}
}