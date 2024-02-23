import java.util.Arrays;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        long min = 1L;
        long max = (long)times[times.length - 1] * n;
        long answer = max;
        long mid, passed;
        while(min <= max) {
            passed = 0L;
            mid = (min + max) / 2;
            for (int time : times)
                passed += mid / time;
            if(passed >= n) {
                answer = mid;
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return answer;
    }
}