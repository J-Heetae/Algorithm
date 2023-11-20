import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
        for(int work : works) {
            pq.offer(work);
        }
        
        for(int i=0; i<n; i++) {
            if(pq.isEmpty()) break;

            int time = pq.poll() - 1;
            
            if(time > 0) pq.offer(time);
        }
        
        while(!pq.isEmpty()) {
            answer += (long)Math.pow(pq.poll(),2);
        }
        
        return answer;
    }
}