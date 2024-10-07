import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int length = diffs.length;
        int min = 1;
        int max = 100_000;
        int minLevel = Integer.MAX_VALUE;
        while(min <= max) {
            int mid = (min + max) / 2;
            long totalTime = 0;
            for(int i=0; i<length; i++) {
                int diff = diffs[i];
                int time = times[i];
                if(diff <= mid) {
                    totalTime += time;
                } else {
                    totalTime += time + (diff - mid) * (times[i - 1] + time);
                }
            }
            if(totalTime <= limit) {
                minLevel = Math.min(minLevel, mid);
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return minLevel;
    }
}