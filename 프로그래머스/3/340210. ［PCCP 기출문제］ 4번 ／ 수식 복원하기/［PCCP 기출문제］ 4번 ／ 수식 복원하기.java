import java.util.*;

class Solution {

    static int minimum = 2;

    public String[] solution(String[] expressions) {
        boolean[] erased = new boolean[expressions.length];
        int erasedNum = 0;
        for (int i = 0; i < expressions.length; i++) {
            String cur = expressions[i];
            for (int j = 9; j > minimum; j--) {
                if (cur.contains(String.valueOf(j - 1))) {
                    minimum = j;
                    break;
                }
            }
            if (cur.contains("X")) {
                erased[i] = true;
                erasedNum++;
            }
        }

        boolean[] possibleBases = new boolean[10];
        for (int i = minimum; i <= 9; i++) {
            possibleBases[i] = true;
        }
        

        for (int i = 0; i < expressions.length; i++) {
            if (!erased[i]) {
                String[] split = expressions[i].split(" ");
                for (int j = minimum; j <= 9; j++) {
                    if (possibleBases[j]) {
                        if (!calculate(split[0], split[2], split[1], j).equals(split[4])) {
                            possibleBases[j] = false;
                        }
                    }
                }
            }
        }

        int idx = 0;
        String[] answer = new String[erasedNum];
        for (int i = 0; i < expressions.length; i++) {
            if (erased[i]) {
                HashSet<String> results = new HashSet<>();
                String[] split = expressions[i].split(" ");
                for (int j = minimum; j <= 9; j++) {
                    if (possibleBases[j]) {
                        results.add(calculate(split[0], split[2], split[1], j));
                    }
                }
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j <= 3; j++) {
                    sb.append(split[j]).append(" ");
                }
                if (results.size() == 1) {
                    for (String result : results) {
                        sb.append(result);
                    }
                } else {
                    sb.append("?");
                }
                answer[idx++] = sb.toString();
            }
        }
        return answer;
    }

    String calculate(String left, String right, String operator, int base) {
        int leftInt = Integer.parseInt(left, base);
        int rightInt = Integer.parseInt(right, base);
        int result = 0;
        if (operator.equals("+")) {
            result = leftInt + rightInt;
        } else {
            result = leftInt - rightInt;
        }
        return Integer.toString(result, base);
    }
}