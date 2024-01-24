import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> asc = new PriorityQueue<>();
        PriorityQueue<Integer> desc = new PriorityQueue<>(Collections.reverseOrder());

        StringTokenizer st;
        for (String operation : operations) {
            st = new StringTokenizer(operation);

            String command = st.nextToken();
            int value = Integer.parseInt(st.nextToken());

            switch (command) {
                case "I":
                    asc.offer(value);
                    desc.offer(value);
                    break;
                case "D":
                    if (asc.isEmpty())
                        break;
                    if (value == 1)
                        asc.remove(desc.poll());
                    else
                        desc.remove(asc.poll());
            }
        }

        if(asc.isEmpty()) return new int[] {0, 0};
        return new int[] {desc.peek(), asc.peek()};
    }
}