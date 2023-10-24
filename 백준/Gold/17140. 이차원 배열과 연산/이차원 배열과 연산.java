import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {

	static boolean find = false;
	static int answer = -1;
	static int row,col, targetNum;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		targetNum = Integer.parseInt(st.nextToken());

		arr = new int[3][3];

		for(int i=0; i<3; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		if(arr.length > row-1 && arr[0].length > col -1 && arr[row-1][col-1] == targetNum) {
			answer = 0;
		} else {
			int count = 0;
			while(++count <= 100) {
				if (arr.length >= arr[0].length) R();
				else C();
				if(find) {
					answer = count;
					break;
				}
			}
		}
		System.out.println(answer);
	}

	private static void R() {
		ArrayList<Integer>[] tmpList = new ArrayList[arr.length];
		int maxSize = 0;
		for(int i=0; i<arr.length; i++) {
			tmpList[i] = new ArrayList<>();
			int[] countArr = new int[101];

			int maxNum = 0;
			for(int j=0; j<arr[i].length; j++) {
				maxNum = Math.max(maxNum, arr[i][j]);
				countArr[arr[i][j]]++;
			}
			ArrayList<Number> numberList = new ArrayList<>();
			for(int j=1; j<=maxNum; j++) {
				if(countArr[j] > 0) {
					numberList.add(new Number(j, countArr[j]));
				}
			}
			Collections.sort(numberList);
			int size = numberList.size();
			for (Number cur : numberList) {
				tmpList[i].add(cur.value);
				tmpList[i].add(cur.count);
			}
			maxSize = Math.max(maxSize, size);
		}
		int[][] tmpArr = new int[arr.length][Math.min(maxSize * 2, 100)];
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<Math.min(tmpList[i].size(),100); j++) {
				tmpArr[i][j] = tmpList[i].get(j);
			}
		}
		arr = tmpArr;
		if(arr.length > row-1 && arr[0].length > col -1 && arr[row-1][col-1] == targetNum) {
			find = true;
		}
	}

	private static void C() {
		ArrayList<Integer>[] tmpList = new ArrayList[arr[0].length];
		int maxSize = 0;
		for(int i=0; i<arr[0].length; i++) {
			tmpList[i] = new ArrayList<>();
			int[] countArr = new int[101];

			int maxNum = 0;
			for(int j=0; j<arr.length; j++) {
				int curNum = arr[j][i];
				maxNum = Math.max(maxNum, curNum);
				countArr[curNum]++;
			}
			ArrayList<Number> numberList = new ArrayList<>();
			for(int j=1; j<=maxNum; j++) {
				if(countArr[j] > 0) {
					numberList.add(new Number(j, countArr[j]));
				}
			}
			Collections.sort(numberList);
			int size = numberList.size();
			for (Number cur : numberList) {
				tmpList[i].add(cur.value);
				tmpList[i].add(cur.count);
			}
			maxSize = Math.max(maxSize, size);
		}
		int[][] tmpArr = new int[Math.min(maxSize * 2, 100)][arr[0].length];
		for(int i=0; i<arr[0].length; i++) {
			for(int j=0; j<Math.min(tmpList[i].size(),100); j++) {
				tmpArr[j][i] = tmpList[i].get(j);
			}
		}
		arr = tmpArr;
		if(arr.length > row-1 && arr[0].length > col -1 && arr[row-1][col-1] == targetNum) {
			find = true;
		}
	}

	private static class Number implements Comparable<Number> {
		int value, count;

		public Number(int value, int count) {
			this.value = value;
			this.count = count;
		}

		@Override
		public int compareTo(Number o) {
			if(this.count == o.count) {
				return this.value - o.value;
			}
			return this.count - o.count;
		}
	}
}