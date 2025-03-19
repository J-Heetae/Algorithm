import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	
	static class Box {
		int num, front, back;
		
		public Box(int num) {
			this.num = num;
			this.front = -1;
			this.back = -1;
		}
	}
	
	static Deque<Integer>[] belts;
	static Box[] boxes;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int orderCount = stoi(st.nextToken());
		
		for(int i=0; i<orderCount; i++) {
			st = new StringTokenizer(br.readLine());
			int order = stoi(st.nextToken());
			int result = 0;
			
			if(order == 100) {
				int beltSize = stoi(st.nextToken());
				int boxSize = stoi(st.nextToken());
				int[] beltNums = new int[boxSize];
				for(int j=0; j<boxSize; j++) {
					beltNums[j] = stoi(st.nextToken());
				}
				init(beltSize, boxSize, beltNums);
				continue;
			}
			
			if(order == 200) {
				int from = stoi(st.nextToken());
				int to = stoi(st.nextToken());
				result = moveAllBoxes(from, to);
			}
			
			if(order == 300) {
				int from = stoi(st.nextToken());
				int to = stoi(st.nextToken());
				result = switchFirstBoxes(from, to);
			}
			
			if(order == 400) {
				int from = stoi(st.nextToken());
				int to = stoi(st.nextToken());
				result = devideBoxes(from, to);
			}
			
			if(order == 500) {
				int boxNum = stoi(st.nextToken());
				result = getBoxInfo(boxNum);
			}
			
			if(order == 600) {
				int beltNum = stoi(st.nextToken());
				result = getBeltInfo(beltNum);
			}
			
			sb.append(result).append("\n");
		}
		System.out.println(sb);
	}
	
	static void init(int beltSize, int boxSize, int[] beltNums) {
		belts = new Deque[beltSize + 1];
		boxes = new Box[boxSize + 1];
		
		for(int i=1; i<=beltSize; i++) {
			belts[i] = new ArrayDeque<>();
		}
		
		for(int i=1; i<=boxSize; i++) {
			boxes[i] = new Box(i);
		}
		
		for(int i=1; i<=beltNums.length; i++) {
			addLast(i, beltNums[i-1]);
		}
	}
	
	static int moveAllBoxes(int from, int to) {
		int size = belts[from].size();
		for(int i=0; i<size; i++) {
			addFirst(pollLast(from), to);
		}
		return belts[to].size();
	}
	
	static int switchFirstBoxes(int from, int to) {
		int fromFirst = pollFirst(from);
		int toFirst = pollFirst(to);
		
		if(fromFirst != 0) {
			addFirst(fromFirst, to);
		}
		
		if(toFirst != 0) {
			addFirst(toFirst, from);
		}

		return belts[to].size();
	}
	
	static int devideBoxes(int from, int to) {
		int boxCount = belts[from].size();
		if(boxCount > 1) {
			int move = (int) Math.floor(boxCount / 2);
			for(int i=0; i<move; i++) {
				addFirst(pollFirst(from), to);
			}
		}
		return belts[to].size();
	}
	
	static int getBoxInfo(int boxNum) {
		Box box = boxes[boxNum];
		return box.front + (2 * box.back);
	}
	
	static int getBeltInfo(int beltNum) {
		Deque<Integer> belt = belts[beltNum];
		if(belt.isEmpty()) {
			return -1 + (2 * -1) + (3 * 0);
		}
		return belt.getFirst() + (2 * belt.getLast()) + (3 * belt.size());
	}
	
	static void addFirst(int boxNum, int beltNum) {
		Deque<Integer> belt = belts[beltNum];
		int prefirstBoxNum = -1;
		
		if(!belt.isEmpty()) {
			prefirstBoxNum = belt.getFirst();
			boxes[prefirstBoxNum].front = boxNum;
		}
		
		belt.addFirst(boxNum);
		boxes[boxNum].front = -1;
		boxes[boxNum].back = prefirstBoxNum;
	}
	
	static void addLast(int boxNum, int beltNum) {
		Deque<Integer> belt = belts[beltNum];
		int preLastBoxNum = -1;
		
		if(!belt.isEmpty()) {
			preLastBoxNum = belt.getLast();
			boxes[preLastBoxNum].back = boxNum;
		}
		
		belt.addLast(boxNum);
		boxes[boxNum].front = preLastBoxNum;
		boxes[boxNum].back = -1;
	}
	
	static int pollFirst(int beltNum) {
		Deque<Integer> belt = belts[beltNum];
		int pollBoxNum = 0;
		
		if(!belt.isEmpty()) {
			pollBoxNum = belt.pollFirst();
			boxes[pollBoxNum].front = -1;
			boxes[pollBoxNum].back = -1;
			
			if(!belt.isEmpty()) {
				int firstBoxNum = belt.getFirst();
				boxes[firstBoxNum].front = -1;
			}
		}
		return pollBoxNum;
	}
	
	static int pollLast(int beltNum) {
		Deque<Integer> belt = belts[beltNum];
		int pollBoxNum = 0;
		
		if(!belt.isEmpty()) {
			pollBoxNum = belt.pollLast();
			boxes[pollBoxNum].front = -1;
			boxes[pollBoxNum].back = -1;
			
			if(!belt.isEmpty()) {
				int lastBoxNum = belt.getLast();
				boxes[lastBoxNum].back = -1;
			}
		}
		return pollBoxNum;
	}
	
	private static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
