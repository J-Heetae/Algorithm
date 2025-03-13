import java.io.*;
import java.util.*;

public class Main {
	
	static final int[] DX = {0, 1, 1, 1, 0, -1, -1, -1};
	static final int[] DY = {1, 1, 0, -1, -1, -1, 0, 1};
	
	static int N, M, K, currTurn;
	static Cannon[][] map;
	
	public static class Cannon {
		int power;
		int lastAttack;
		boolean repair;
		
		public Cannon(int power) {
			this.power = power;
			this.lastAttack = 0;
			this.repair = true;
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
				map[i][j] = new Cannon(power);
			}
		}
		
		while(currTurn < K) {
			currTurn++;
			int[] attacker = pickAttacker();
			int[] defender = pickDefender();
			attack(attacker, defender);
			repairCannon();
		}
		
		System.out.print(getMaxPower());
	}
	
	public static int getMaxPower() {
		int maxPower = Integer.MIN_VALUE;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				maxPower = Math.max(maxPower, map[i][j].power);
			}
		}
		return maxPower;
	}
	
	public static int[] pickAttacker() {
		int x = 0;
		int y = 0;
		int minPower = Integer.MAX_VALUE;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				Cannon curr = map[i][j];
				
				if(curr.power == 0) { //부서진 포탑 넘어가기
					continue;
				}
				
				if(curr.power < minPower) {
					x = i;
					y = j;
					minPower = curr.power;
					continue;
				}
				
				if(curr.power == minPower) {
					if(curr.lastAttack > map[x][y].lastAttack) {
						x = i;
						y = j;
						minPower = curr.power;
						continue;
					}
				}
			}
		}
		
		map[x][y].power += N + M;
		map[x][y].lastAttack = currTurn;
		map[x][y].repair = false;
		
		return new int[]{x, y};
	}
	
	public static int[] pickDefender() {
		int x = 0;
		int y = 0;
		int maxPower = Integer.MIN_VALUE;
		
		for(int i=N-1; i>=0; i--) {
			for(int j=M-1; j>=0; j--) {
				Cannon curr = map[i][j];
				
				if(curr.power == 0) { //부서진 포탑 넘어가기
					continue;
				}
				
				if(curr.power > maxPower) {
					x = i;
					y = j;
					maxPower = curr.power;
					continue;
				}
				
				if(curr.power == maxPower) {
					if(curr.lastAttack < map[x][y].lastAttack) {
						x = i;
						y = j;
						maxPower = curr.power;
						continue;
					}
				}
			}
		}
		
		map[x][y].repair = false;
		
		return new int[]{x, y};
	}
	
	public static void attack(int[] attacker, int[] defender) {
		int[][][] path = lazer(attacker, defender);
		int damage = map[attacker[0]][attacker[1]].power;
		
		map[defender[0]][defender[1]].power = Math.max(0, map[defender[0]][defender[1]].power - damage);	
		
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
			int nx = defender[0] + DX[i];
			int ny = defender[1] + DY[i];
			
			nx = fixCoor(nx, N);
			ny = fixCoor(ny, M);
			
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
				int nx = curr[0] + DX[i];
				int ny = curr[1] + DY[i];
				
				nx = fixCoor(nx, N);
				ny = fixCoor(ny, M);
				
				if(path[nx][ny][0] != -1) { //최단 경로가 아닌 경우 넘어가기
					continue;
				}
				
				if(map[nx][ny].power == 0) { //부서진 포탑 넘어가기
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
				if(map[i][j].power != 0 && map[i][j].repair) {
					map[i][j].power += 1;
				}
			}
		}
	}
	
	public static int fixCoor(int coor, int max) {
		if(coor < 0) {
			return max - 1;
		}
		
		if(coor == max) {
			return 0;
		}
		
		return coor;
	}
	
}