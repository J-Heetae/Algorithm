import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution {
    public int[] solution(String[] operations) {
        List<Integer> list = new LinkedList<>();

        StringTokenizer st;
        for(String operation : operations) {
            st = new StringTokenizer(operation);

            String order = st.nextToken();
            int value = Integer.parseInt(st.nextToken());

            if(order.equals("I")) {
                list.add(value);
                Collections.sort(list);
                continue;
            }

            if(value == -1 && !list.isEmpty()) {
                list.remove(0);
            } else if(value == 1 && !list.isEmpty()) {
                list.remove(list.size() - 1);
            }
        }

        int[] answer = {0, 0};
        if(!list.isEmpty()) {
            answer[0] = list.get(list.size() - 1);
            answer[1] = list.get(0);
        }
        return answer;
    }
}