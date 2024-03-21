import java.util.Arrays;

class Solution {
    public int solution(int[][] scores) {
        final int[] WANHO = scores[0];
        final int WANHO_SUM = WANHO[0] + WANHO[1];

        Arrays.sort(scores, (a, b) -> {
            if (a[0] == b[0])
                return a[1] - b[1];
            return b[0] - a[0];
        });

        int rank = 1;
        int max = scores[0][1];
        for (int[] score : scores) {
            if(score[1] < max) {
                if(Arrays.equals(score, WANHO))
                    return -1;
                continue;
            } else if (score[0] + score[1] > WANHO_SUM){
                rank++;
            }
            max = score[1];
        }
        return rank;
    }
}