import java.util.*;

class Solution {
    public String[] solution(String[][] tickets) {
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for (String[] ticket : tickets) { 
            if (!graph.containsKey(ticket[0])) {
                graph.put(ticket[0], new PriorityQueue<>());
            }
            graph.get(ticket[0]).add(ticket[1]);
        }

        List<String> answerList = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        stack.push("ICN");

        while (!stack.isEmpty()) {
            String top = stack.peek();
            if (!graph.containsKey(top) || graph.get(top).isEmpty()) {
                answerList.add(stack.pop());
            } else {
                stack.push(graph.get(top).poll());
            }
        }

        Collections.reverse(answerList);
        String[] answer = new String[answerList.size()];
        answerList.toArray(answer);
        return answer;
    }
}