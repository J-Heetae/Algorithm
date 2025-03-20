import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	static Scanner sc = new Scanner(System.in);
	
	static final int MAX_N = 100_000;
	static final int MAX_M = 100_000;
	
	static int[] prev = new int[MAX_M + 1];
	static int[] next = new int[MAX_M + 1];
	
	static int[] boxCount = new int[MAX_N + 1];
	static int[] head = new int[MAX_N + 1];
	static int[] tail = new int[MAX_N + 1];
	
	
    public static void main(String[] args) {
        int orderNum = sc.nextInt();
        
        while(orderNum-- > 0) {
        	int order = sc.nextInt();
        	
        	if(order == 100) {
        		init();
        	} else if (order == 200) {
        		moveAll();
        	} else if (order == 300) {
        		changeHead();
        	} else if (order == 400) {
        		divide();
        	} else if (order == 500) {
        		getBoxInfo();
        	} else if (order == 600) {
        		getBeltInfo();
        	}
        }
    }

	private static void getBeltInfo() {
		int belt = sc.nextInt();
		int a = (head[belt] == 0)? -1 : head[belt];
		int b = (tail[belt] == 0)? -1 : tail[belt];
		int c = boxCount[belt];
		System.out.println(a + 2 * b + 3 * c);
		return;
	}

	private static void getBoxInfo() {
		int box = sc.nextInt();
		int a = (prev[box] == 0)? -1 : prev[box];
		int b = (next[box] == 0)? -1 : next[box];
		System.out.println(a + 2 * b);
		return;
	}

	private static void divide() {
		int src = sc.nextInt();
		int dst = sc.nextInt();
		
		if(boxCount[src] > 1) {
			int srcHead = head[src];
			int lastBox = head[src];
			
			int size = boxCount[src] / 2;
			for(int i=1; i<size; i++) {
				lastBox = next[lastBox];
			}
			
			head[src] = next[lastBox];
			prev[head[src]] = 0;
			
			if(boxCount[dst] == 0) {
				tail[dst] = lastBox;
				next[lastBox] = 0;
				
			} else {
				int dstHead = head[dst];
				prev[dstHead] = lastBox;
				next[lastBox] = dstHead;
			}
			
			head[dst] = srcHead;
			
			boxCount[src] -= size;
			boxCount[dst] += size;
		}
		System.out.println(boxCount[dst]);
		return;
	}

	private static void changeHead() {
		int src = sc.nextInt();
		int dst = sc.nextInt();
		
		int srcHead = pollHead(src);
		int dstHead = pollHead(dst);
		
		addHead(src, dstHead);
		addHead(dst, srcHead);
		
		System.out.println(boxCount[dst]);
	}
	
	private static int pollHead(int belt) {
		if(boxCount[belt] == 0) {
			return 0;
		}
		
		int currHead = head[belt];
		
		if(boxCount[belt] == 1) {
			head[belt] = 0;
			tail[belt] = 0;
		} else {
			head[belt] = next[currHead];
			prev[head[belt]] = 0;
		}
		
		next[currHead] = 0;
		prev[currHead] = 0;
		
		boxCount[belt]--;
		
		return currHead;
	}
	
	private static void addHead(int belt, int box) {
		if(box == 0) {
			return;
		}
		
		if(boxCount[belt] == 0) {
			head[belt] = box;
			tail[belt] = box;
		} else {
			int prevHead = head[belt];
			head[belt] = box;
			next[box] = prevHead;
			prev[prevHead] = box;
		}
		
		boxCount[belt]++;
		
		return;
	}

	private static void moveAll() {
		int src = sc.nextInt();
		int dst = sc.nextInt();
		
		if(boxCount[src] == 0) {
			System.out.println(boxCount[dst]);
			return;
		}
		
		if(boxCount[dst] == 0) {
			boxCount[dst] = boxCount[src];
			head[dst] = head[src];
			tail[dst] = tail[src];
		} else {
			int dstHead = head[dst];
			int srcTail = tail[src];
			prev[dstHead] = srcTail;
			next[srcTail] = dstHead;
			
			boxCount[dst] += boxCount[src];
			head[dst] = head[src];
		}
		boxCount[src] = 0;
		head[src] = 0;
		tail[src] = 0;
		
		System.out.println(boxCount[dst]);
		return;
	}

	private static void init() {
		int beltNum = sc.nextInt();
		int boxNum = sc.nextInt();
		
		ArrayList<Integer>[] belts = new ArrayList[beltNum + 1];
		for(int i=1; i<=beltNum; i++) {
			belts[i] = new ArrayList<>();
		}
		
		for(int i=1; i<=boxNum; i++) {
			belts[sc.nextInt()].add(i);
		}
		
		for(int i=1; i<=beltNum; i++) {			
			if(!belts[i].isEmpty()) {
				boxCount[i] = belts[i].size();
				head[i] = belts[i].get(0);
				tail[i] = belts[i].get(belts[i].size() - 1);
				
				for(int j=0; j<belts[i].size()-1; j++) {
					next[belts[i].get(j)] = belts[i].get(j + 1);
					prev[belts[i].get(j + 1)] = belts[i].get(j);
				}
			}
		}
	}
}