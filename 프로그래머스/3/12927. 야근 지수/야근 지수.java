import java.util.Arrays;
import java.util.Collections;

class Solution {
    public long solution(int n, int[] works) {
        int length = works.length - 1;
        Arrays.sort(works);
        int max = works[length];

        while(n > 0 && max != 0) {
            for (int i = length; i>=0; i-- ) {
                if(n == 0) break;

                if(works[i] >= max) {
                    works[i]--;
                    n--;
                } else {
                    max = works[length];
                    break;
                }
            }
        }

        long answer = 0L;
        for (int work : works) {
            answer += (long)work * work;
        }
        return answer;
    }
}