import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution("100-200*300-500+20");
    }
    long maxReward = Integer.MIN_VALUE;
    HashMap<Character, Integer> map = new HashMap<>();
    Stack<Long> operandStack;
    Stack<Character> operatorStack;
    ArrayList<Long> operandList = new ArrayList<>();
    ArrayList<Character> operatorList = new ArrayList<>();
    int[] priority;

    public long solution(String expression) {
        int mapIdx = 0;
        int startIdx = 0;
        for (int k = 0; k < expression.length(); k++) {
            if (expression.charAt(k) == '+' || expression.charAt(k) == '-' || expression.charAt(k) == '*') {
                if (!map.containsKey(expression.charAt(k)))
                    map.put(expression.charAt(k), mapIdx++);
                operatorList.add(expression.charAt(k));
                operandList.add(Long.valueOf(expression.substring(startIdx, k)));
                startIdx = k + 1;
            }
        }
        operandList.add(Long.valueOf(expression.substring(startIdx))); //마지막 숫자 넣어주기
        priority = new int[map.size()];
        makePriority(1);
        return maxReward;
    }

    void makePriority(int curPriority) {
        if (curPriority > priority.length) {
            getMaxReward();
            return;
        }

        for (int i = 0; i < priority.length; i++) {
            if (priority[i] == 0) {
                priority[i] = curPriority;
                makePriority(curPriority + 1);
                priority[i] = 0;
            }
        }
    }

    void getMaxReward() {
        operandStack = new Stack<>();
        operatorStack = new Stack<>();
        int operandIdx = 0;
        int operatorIdx = 0;

        while (operandIdx < operandList.size()) {
            operandStack.add(operandList.get(operandIdx++));

            if (operatorIdx < operatorList.size()) {
                while (operatorStack.size() != 0 &&
                    priority[map.get(operatorStack.peek())] <= priority[map.get(operatorList.get(operatorIdx))]) {
                    calculate();
                }
                operatorStack.add(operatorList.get(operatorIdx++));
            }
        }

        while (!operatorStack.isEmpty()) {
            calculate();
        }
        maxReward = Math.max(maxReward, Math.abs(operandStack.peek()));
    }

    void calculate() {
        long b = operandStack.pop();
        long a = operandStack.pop();

        char curOperator = operatorStack.pop();
        switch (curOperator) {
            case '+':
                operandStack.add(a + b);
                break;
            case '-':
                operandStack.add(a - b);
                break;
            case '*':
                operandStack.add(a * b);
        }
    }
}