public class Solution {
    static final int ATTACK = 1;

    static int N, M, answer;

    static int[][] prefix;

    public int solution(int[][] map, int[][] skills) {
        N = map.length;
        M = map[0].length;
        prefix = new int[N + 1][M + 1];

        for (int[] skill : skills) {
            int value = skill[5] * ((skill[0] == ATTACK)? -1 : 1);
            prefix[skill[1]][skill[2]] += value;
            prefix[skill[1]][skill[4]+1] -= value;
            prefix[skill[3]+1][skill[2]] -= value;
            prefix[skill[3]+1][skill[4]+1] += value;
        }
        sum();

        for(int r=0; r<N; r++) {
            for(int c=0; c<M; c++) {
                map[r][c] += prefix[r][c];
                if(map[r][c] > 0) {
                    answer++;
                }
            }
        }
        return answer;
    }

    public void sum() {
        for(int c=0; c<M; c++) {
            for(int r=1; r<=N; r++) {
                prefix[r][c] += prefix[r-1][c];
            }
        }

        for(int r=0; r<N; r++) {
            for(int c=1; c<=M; c++) {
                prefix[r][c] += prefix[r][c-1];
            }
        }
    }
}