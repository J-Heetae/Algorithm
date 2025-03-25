import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	
	static final int[] DX = {-1, 0, 1, 0};
	static final int[] DY = {0, 1, 0, -1};
	
	static int n, m, k;
	static int[] scores;
	static Player[] players;
	static int[][] map;
	static PriorityQueue<Integer>[][] gunMap;
	static class Player {
		int x, y, dir, power, gunPower;
		
		public Player(int x, int y, int dir, int power) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.power = power;
		}
	}
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	n = sc.nextInt();
    	m = sc.nextInt();
    	k = sc.nextInt();
    	
    	scores = new int[m + 1];
    	players = new Player[m + 1];
    	
    	map = new int[n][n];
    	gunMap = new PriorityQueue[n][n];
    	
    	for(int i=0; i<n; i++) {
    		for(int j=0; j<n; j++) {
    			gunMap[i][j] = new PriorityQueue<>(Comparator.reverseOrder());
    			
    			int gun = sc.nextInt();
    			if(gun != 0) {
    				gunMap[i][j].add(gun);
    			}
    		}
    	}
    	
    	for(int i=1; i<=m; i++) {
    		int x = sc.nextInt() - 1;
    		int y = sc.nextInt() - 1;
    		int dir = sc.nextInt();
    		int power = sc.nextInt();
    		map[x][y] = i;
    		players[i] = new Player(x, y, dir, power);
    	}
    	
    	while(k-- > 0) {
    		move();
    		
    	}
    	
    	for(int i=1; i<=m; i++) {
    		System.out.print(scores[i] + " ");
    	}
    }

	private static void move() {
		for(int i=1; i<=m; i++) {
			Player curr = players[i];
			
			int nx, ny;
			while(true) {
				nx = curr.x + DX[curr.dir];
				ny = curr.y + DY[curr.dir];
				
				if(nx < 0 || nx >= n || ny < 0 || ny >= n) {
					curr.dir = (curr.dir + 2) % 4;
					continue;
				}
				break;
			}
			map[curr.x][curr.y] = 0;
			curr.x = nx;
			curr.y = ny;
			
			if(map[nx][ny] != 0) {
				fight(i, map[nx][ny]);
			} else {
				map[curr.x][curr.y] = i;
				pickGun(i, nx, ny);
			}
		}
		
	}

	private static void pickGun(int playerIdx, int x, int y) {
		if(gunMap[x][y].isEmpty()) {
			return;
		}
		
		Player curr = players[playerIdx];
		if(curr.gunPower == 0) { //기존에 총이 없으면?
			curr.gunPower = gunMap[x][y].poll();
		} else {
			if(curr.gunPower < gunMap[x][y].peek()) {
				int origin = curr.gunPower;
				curr.gunPower = gunMap[x][y].poll();
				gunMap[x][y].add(origin);
			}
		}
	}

	private static void fight(int player1Idx, int player2Idx) {
		Player p1 = players[player1Idx];
		Player p2 = players[player2Idx];
		
		int winnerIdx = 0;
		int score = 0;
		
		if(p1.power + p1.gunPower == p2.power + p2.gunPower) {
			winnerIdx = (p1.power > p2.power)? player1Idx : player2Idx;
		} else {
			winnerIdx = (p1.power + p1.gunPower > p2.power + p2.gunPower)? player1Idx : player2Idx;
			score = Math.abs((p1.power + p1.gunPower) - (p2.power + p2.gunPower));
		}
		int loserIdx = (winnerIdx == player1Idx)? player2Idx : player1Idx;
		
		Player winner = players[winnerIdx];
		Player loser = players[loserIdx];
		
		//진 플레이어 총 내려놓기
		if(loser.gunPower != 0) {
			gunMap[loser.x][loser.y].add(loser.gunPower);
			loser.gunPower = 0;
		}
		
		//진 플레이어 이동하기
		for(int i=0; i<4; i++) {
			int nx = loser.x + DX[loser.dir];
			int ny = loser.y + DY[loser.dir];
			
			if(nx < 0 || nx >= n || ny < 0 || ny >= n || map[nx][ny] != 0) {
				loser.dir = (loser.dir + 1) % 4;
				continue;
			}
			map[nx][ny] = loserIdx;
			loser.x = nx;
			loser.y = ny;
			pickGun(loserIdx, nx, ny);
			break;
		}
		
		//이긴 플레이어 점수 획득
		scores[winnerIdx] += score;
		
		//이긴 플레이어 총 고르기
		pickGun(winnerIdx, winner.x, winner.y);
		
		map[winner.x][winner.y] = winnerIdx;
	}

}