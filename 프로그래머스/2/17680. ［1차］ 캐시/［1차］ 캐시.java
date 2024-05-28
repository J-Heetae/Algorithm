import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        Queue<String> que = new LinkedList<>();
        int time = 0;
        for (String city : cities) {
            city = city.toLowerCase();
            if (que.contains(city)) {
                time++;
                que.remove(city);
                que.offer(city);
            } else {
                time += 5;
                que.offer(city);
                if (que.size() > cacheSize) {
                    que.poll();
                }
            }
        }
        return time;
    }
}