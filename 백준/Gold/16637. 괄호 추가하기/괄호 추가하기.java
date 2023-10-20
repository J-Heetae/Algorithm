import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Main {

	static boolean[] bracket;
	static int length;
	static String formula;
	static int maxResult = Integer.MIN_VALUE;
	static Stack<String> stack = new Stack<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		length = Integer.parseInt(br.readLine());

		formula = br.readLine();
		bracket = new boolean[length];
		getResult();
		makeBracket(1);
		System.out.println(maxResult);
	}

	private static void makeBracket(int idx) {
		if(idx == length) {
			getResult();
			return;
		}
		if((idx - 2) < 0 || !bracket[idx - 2]) {
			bracket[idx] = true;
			makeBracket(idx + 2);
			bracket[idx] = false;
		}
		makeBracket(idx+2);
	}

	private static void getResult() {
		for(int i=0; i<length; i++) {
			if(i%2!=0 && bracket[i]) {
				char cur = formula.charAt(i);
				int before = Integer.parseInt(stack.pop());
				int next = Character.getNumericValue(formula.charAt(++i));

				if(cur == '+') {
					stack.add(String.valueOf(before + next));
				} else if(cur == '-') {
					stack.add(String.valueOf(before - next));
				} else if(cur == '*'){
					stack.add(String.valueOf(before * next));
				}
			} else {
				stack.add(String.valueOf(formula.charAt(i)));
			}
		}

		Stack<String> stack2 = new Stack<>();
		while(!stack.isEmpty()) {
			stack2.add(stack.pop());
		}
		int result = Integer.parseInt(stack2.pop());
		int next = 0;
		while(!stack2.isEmpty()) {
			String cur = stack2.pop();
			next = Integer.parseInt(stack2.pop());

			if(cur.equals("+")) {
				result += next;
			} else if(cur.equals("-")) {
				result -= next;
			} else if(cur.equals("*")){
				result *= next;
			}
		}
		maxResult = Math.max(maxResult, result);
	}
}