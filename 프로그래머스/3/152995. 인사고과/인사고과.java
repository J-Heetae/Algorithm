import java.util.Arrays;

class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution(new int[][]{{2, 2}, {1, 4}, {3, 2}, {3, 2}, {2, 1}});
    }

    static final int MAX_SCORE = 100_000;
    public int solution(int[][] scores) {
        int length = scores.length;
        int[] wanhoScore = scores[0];

        Arrays.sort(scores, (a, b) -> {
            if (a[0] == b[0])
                return Integer.compare(b[1], a[1]);
            return Integer.compare(b[0], a[0]);
        });

        int idx = 100_001;
        int[] maxArr = new int[MAX_SCORE + 1];
        Arrays.fill(maxArr, Integer.MIN_VALUE);
        for(int i=0; i<length; i++) {
            if (scores[i][0] < idx) {
                idx = scores[i][0];
                maxArr[scores[i][0]] = scores[i][1];
            }
        }

        int maxNum = scores[0][0];
        int curNum = scores[0][0];
        boolean curOut = false;
        boolean[] out = new boolean[length];
        for(int i=0; i<length; i++) {
            if(scores[i][0] == curNum && curOut) {
                if(scores[i][0] == wanhoScore[0] && scores[i][1] == wanhoScore[1])
                    return -1;
                out[i] = true;
                continue;
            }

            if(scores[i][0] < curNum) {
                curNum = scores[i][0];
                curOut = false;
            }

            for(int j=curNum+1; j<=maxNum; j++) {
                if(scores[i][1] < maxArr[j]) {
                    if(scores[i][0] == wanhoScore[0] && scores[i][1] == wanhoScore[1])
                        return -1;
                    out[i] = true;
                    curOut = true;
                    break;
                }
            }
        }

        int wanhoSum = wanhoScore[0] + wanhoScore[1];
        int rank = 1;
        for(int i=0; i<length; i++) {
            if (!out[i] && scores[i][0] + scores[i][1] > wanhoSum)
                rank++;
        }
        return rank;
    }
}