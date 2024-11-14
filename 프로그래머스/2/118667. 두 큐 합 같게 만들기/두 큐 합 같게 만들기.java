import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        long sum = 0L, que1Sum = 0L, que2Sum = 0L;
        Queue<Long> que1 = new LinkedList<>();
        Queue<Long> que2 = new LinkedList<>();
        for(int i = 0; i < queue1.length; i++) {
            long queue1Long = (long)queue1[i];
            long queue2Long = (long)queue2[i];
            que1.offer(queue1Long);
            que2.offer(queue2Long);
            que1Sum += queue1Long;
            que2Sum += queue2Long;
            sum += queue1Long + queue2Long;
        }
        sum /= 2;
        int count = 0;
        
        while(count <= 600_000 && que1Sum != sum) {
            if(que1Sum < sum) {
                long poll = que2.poll();
                if(poll > sum) {
                    count = -1;
                    break;
                }
                que1Sum += poll;
                que2Sum -= poll;
                que1.offer(poll);
            } else {
                long poll = que1.poll();
                if(poll > sum) {
                    count = -1;
                    break;
                }
                que1Sum -= poll;
                que2Sum += poll;
                que2.offer(poll);
            }
            count++;
        }
        return (count == (600_001))? -1 : count;
    }
}