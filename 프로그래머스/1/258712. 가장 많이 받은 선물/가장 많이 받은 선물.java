import java.util.HashMap;
import java.util.StringTokenizer;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        HashMap<String, Integer> friendToIndexMap = new HashMap<>();
        int idx = 0;
        for (String s : friends) {
            friendToIndexMap.put(s, idx++);
        }

        int[][] giveAndTake = new int[idx][idx];
        int[] presentIndex = new int[idx];

        StringTokenizer st;
        for (String gift : gifts) {
            st = new StringTokenizer(gift);
            int giveIdx = friendToIndexMap.get(st.nextToken());
            int takeIdx = friendToIndexMap.get(st.nextToken());

            giveAndTake[giveIdx][takeIdx]++;
            giveAndTake[takeIdx][giveIdx]--;
            presentIndex[giveIdx]++;
            presentIndex[takeIdx]--;
        }

        int max = Integer.MIN_VALUE;
        for (int a = 0; a < idx; a++) {
            int cur = 0;
            for (int b = 0; b < idx; b++) {
                if (a == b)
                    continue;

                if (giveAndTake[a][b] > 0 ||
                    (giveAndTake[a][b] == 0 && presentIndex[a] > presentIndex[b]))
                    cur++;
            }
            max = Math.max(max, cur);
        }
        return max;
    }
}