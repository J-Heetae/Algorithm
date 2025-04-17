import java.util.*;

public class Main {

	static final int[] DX = {-1, 1, 0, 0};
	static final int[] DY = {0, 0, -1, 1};
	
	static final int T = 1 << 0;
	static final int C = 1 << 1;
	static final int M = 1 << 2;
	static final HashMap<Integer, Integer> printIdx = new HashMap<>();

	static {
	    printIdx.put(T | C | M, 0);
	    printIdx.put(T | C, 1);
	    printIdx.put(T | M, 2);
	    printIdx.put(C | M, 3);
	    printIdx.put(M, 4);
	    printIdx.put(C, 5);
	    printIdx.put(T, 6); 
	}
	
	static int N;
	static int[][] foods;
	static int[][] powers;
	
	static boolean[][] pass;
	static ArrayList<int[]> leaders1 = new ArrayList<>();
	static ArrayList<int[]> leaders2 = new ArrayList<>();
	static ArrayList<int[]> leaders3 = new ArrayList<>();
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int day = sc.nextInt();
        
        foods = new int[N][N];
        powers = new int[N][N];
        
        sc.nextLine();
        for(int i=0; i<N; i++) {
        	String line = sc.nextLine();
        	for(int j=0; j<N; j++) {
        		if(line.charAt(j) == 'T') {
        		    foods[i][j] = T;
        		} else if(line.charAt(j) == 'C') {
        		    foods[i][j] = C;
        		} else {
        		    foods[i][j] = M;
        		}
        	}
        }
        
        for(int i=0; i<N; i++) {
        	for(int j=0; j<N; j++) {
        		int power = sc.nextInt();
        		powers[i][j] = power;
        	}
        }
        
        while(day-- > 0) {
        	init();
        	getPower();
        	makeGroup();
        	evening();
        	printDay();
        }
    }

	private static void init() {
		leaders1.clear();
		leaders2.clear();
		leaders3.clear();
	}

	private static void printDay() {
		int[] printArr = new int[7];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				printArr[printIdx.get(foods[i][j])] += powers[i][j];
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i : printArr) {
			sb.append(i).append(" ");
		}
		System.out.println(sb);
	}

	private static void evening() {
		pass = new boolean[N][N];
		
		Collections.sort(leaders1, comparatorMaker());
		Collections.sort(leaders2, comparatorMaker());
		Collections.sort(leaders3, comparatorMaker());
		
		for(int[] leader : leaders1) {
			mixFood(leader);
		}
		
		for(int[] leader : leaders2) {
			mixFood(leader);
		}
		
		for(int[] leader : leaders3) {
			mixFood(leader);
		}
	}
	
	private static void mixFood(int[] leader) {
		int x = leader[2];
		int y = leader[3];
		
		if(pass[x][y]) { // 전파 당했다면 전파를 하지 않음
			return;
		}
		
		int value = leader[1] - 1;
		int food = leader[0];
		int dir = leader[1] % 4;
		
		powers[x][y] = 1;
		
		int nx = x;
		int ny = y;
		while(value > 0) { // 절박함이 0이 되면 종료
			nx += DX[dir];
			ny += DY[dir];
			
			if(nx < 0 || nx >= N || ny < 0 || ny >= N) { // 격자를 벗어난 경우 종료
				break;
			}
			
			if(foods[nx][ny] == food) { //신봉 음식이 완전히 같은 경우 다음으로
				continue;
			}
			
			// 전파를 당함
			pass[nx][ny] = true;
			
			if(powers[nx][ny] < value) { // 강한 전파
				foods[nx][ny] = food;
	
				powers[nx][ny]++;
				value -= powers[nx][ny];
				
			} else { //약한 전파
				for (int i = 0; i <= 2; i++) {
					if((foods[x][y] & 1 << i) > 0 && (foods[nx][ny] & (1 << i)) == 0) {
						foods[nx][ny] |= (1 << i);
				    }
				}
				powers[nx][ny] += value;
				break;
			}
		}
	}
	
	private static Comparator<int[]> comparatorMaker() {
		return new Comparator<int[]> (){
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1] != o2[1]) return Integer.compare(o2[1], o1[1]);
				if(o1[2] != o2[2]) return Integer.compare(o1[2], o2[2]);
				return Integer.compare(o1[3], o2[3]);
			}
		};
	}

	private static void makeGroup() {
		boolean[][] visited = new boolean[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j]) {
					visited[i][j] = true;
					
					int currFood = foods[i][j];
					
					ArrayList<int[]> members = new ArrayList<>();
					
					Queue<int[]> q = new LinkedList<>();
					q.offer(new int[] {i, j});
					
					while(!q.isEmpty()) {
						int[] poll = q.poll();
						int currX = poll[0];
						int currY = poll[1];

						members.add(new int[] {powers[currX][currY], currX, currY});
						
						for(int d=0; d<4; d++) {
							int nx = currX + DX[d];
							int ny = currY + DY[d];
							
							if(nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) {
								continue;
							}
							
							if(foods[nx][ny] == currFood) {
								visited[nx][ny] = true;
								q.offer(new int[] {nx, ny});
							}
						}
					}
					
					Collections.sort(members, new Comparator<int[]>() {
						@Override
						public int compare(int[] o1, int[] o2) {
							if(o1[0] != o2[0]) return Integer.compare(o2[0], o1[0]);
							if(o1[1] != o2[1]) return Integer.compare(o1[1], o2[1]);
							return Integer.compare(o1[2], o2[2]);
						}
					});
					
					int idx = 1;
					for(int[] m : members) {
						if(idx == 1) { // 대표자
							idx--;
							
							powers[m[1]][m[2]] += members.size() - 1;
							
							int[] currLeader = new int[] {currFood, powers[m[1]][m[2]], m[1], m[2]};
							int count = Integer.bitCount(currFood);
							if(count == 1) {
								leaders1.add(currLeader);
							} else if (count == 2) {
								leaders2.add(currLeader);
							} else {
								leaders3.add(currLeader);
							}
						} else {
							powers[m[1]][m[2]]--;
						}
					}
				}
			}
		}
		
	}

	private static void getPower() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				powers[i][j]++;
			}
		}
	}
}