import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {
	static final int R = 0;
	static final int C = 1;

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

		isFind();
		if(find) {
			answer = 0;
		} else {
			int count = 0;
			while(++count <= 100) {
				if ((arr.length >= arr[0].length)) method(R);
				else method(C);

				if(find) {
					answer = count;
					break;
				}
			}
		}
		System.out.println(answer);
	}

	private static void method(int methodType) {
		int length = (methodType == R) ? arr.length : arr[0].length;

		ArrayList<Integer>[] tmpList = new ArrayList[length];
		for (int i = 0; i < tmpList.length; i++) {
			tmpList[i] = new ArrayList<>();
		}

		int maxSize = 0;
		for(int i=0; i<length; i++) {
			ArrayList<Number> numberList = getNumberList(i, methodType);
			maxSize = Math.max(maxSize, numberList.size());
			Collections.sort(numberList);
			fillList(numberList, tmpList[i]);
		}
		changeArr(maxSize, tmpList, methodType);
		isFind();
	}

	private static void isFind() {
		if(arr.length > row - 1 && arr[0].length > col - 1 && arr[row - 1][col - 1] == targetNum) {
			find = true;
		}
	}

	private static void fillList(ArrayList<Number> numberList, ArrayList<Integer> tmpList) {
		for (Number cur : numberList) {
			tmpList.add(cur.value);
			tmpList.add(cur.count);
		}
	}

	private static ArrayList<Number> getNumberList(int idx, int methodType) {
		ArrayList<Number> numberList = new ArrayList<>();
		int maxNum = 0;
		int[] countArr = new int[101];

		if(methodType == R) {
			for (int j = 0; j < arr[idx].length; j++) {
				countArr[arr[idx][j]]++;
				maxNum = Math.max(maxNum, arr[idx][j]);
			}
		} else {
			for(int j=0; j<arr.length; j++) {
				int curNum = arr[j][idx];
				maxNum = Math.max(maxNum, curNum);
				countArr[curNum]++;
			}
		}

		for(int j=1; j<=maxNum; j++) {
			if(countArr[j] > 0) {
				numberList.add(new Number(j, countArr[j]));
			}
		}
		return numberList;
	}

	private static void changeArr(int maxSize, ArrayList<Integer>[] tmpList, int methodType) {
		int row = (methodType == R) ? arr.length : Math.min(maxSize * 2, 100);
		int col = (methodType == R) ? Math.min(maxSize * 2, 100) : arr[0].length;

		int[][] tmpArr = new int[row][col];

		int limit = (methodType == R) ? row : col;
		for (int i = 0; i < limit; i++) {
			for (int j = 0; j < Math.min(tmpList[i].size(), 100); j++) {
				if(methodType == R) {
					tmpArr[i][j] = tmpList[i].get(j);
				} else {
					tmpArr[j][i] = tmpList[i].get(j);
				}
			}
		}
		arr = tmpArr;
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