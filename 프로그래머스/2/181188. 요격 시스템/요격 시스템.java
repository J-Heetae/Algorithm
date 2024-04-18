import java.util.Arrays;
import java.util.Comparator;

class Solution {
    int minMissile = Integer.MAX_VALUE;
    public int solution(int[][] targets) {
        Arrays.sort(targets, Comparator.comparingInt(a -> a[1]));

        int missileCount = 0;
        double prevMissile = 0;
        for (int[] target : targets) {
            if(target[0] < prevMissile && prevMissile < target[1])
                continue;

            missileCount++;
            prevMissile = target[1] - 0.5;
        }

        return missileCount;
    }
}